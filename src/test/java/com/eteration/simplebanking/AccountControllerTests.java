package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.dto.*;
import com.eteration.simplebanking.services.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAccount() throws Exception {
        String accountNumber = "12345678";
        AccountResponse mockResponse = new AccountResponse("John Doe", accountNumber);
        when(accountService.getByAccountNumber(accountNumber)).thenReturn(mockResponse);


        mockMvc.perform(get("/account/v1/" + accountNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.owner").value("John Doe"))
                .andExpect(jsonPath("$.accountNumber").value(accountNumber));
    }

    @Test
    void testCredit() throws Exception {
        String accountNumber = "12345678";
        Double amount = 100.0;
        CreditRequest creditRequest = new CreditRequest(amount);
        CreditResponse mockResponse = new CreditResponse("OK", "approvalCode123");

        when(accountService.credit(creditRequest, accountNumber)).thenReturn(mockResponse);
        mockMvc.perform(post("/account/v1/credit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creditRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testDebit() throws Exception {
        String accountNumber = "12345678";
        Double amount = 50.0;
        DebitRequest debitRequest = new DebitRequest(amount);
        DebitResponse mockResponse = new DebitResponse("OK", "approvalCode456");

        when(accountService.debit(debitRequest, accountNumber)).thenReturn(mockResponse);

        mockMvc.perform(post("/account/v1/debit/{accountNumber}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debitRequest)))
                .andExpect(status().isOk());
    }
}
