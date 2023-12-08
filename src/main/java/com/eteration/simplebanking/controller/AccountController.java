package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.dto.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/{accountNumber}")
    public AccountResponse getAccount(@PathVariable String accountNumber) {
        return accountService.getByAccountNumber(accountNumber);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<CreditResponse> credit(@PathVariable String accountNumber, @RequestBody CreditRequest creditRequest) {
        return ResponseEntity.ok(accountService.credit(creditRequest, accountNumber));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<DebitResponse> debit(@RequestBody DebitRequest debitRequest, @PathVariable String accountNumber) throws InsufficientBalanceException {
        return ResponseEntity.ok(accountService.debit(debitRequest, accountNumber));
    }

    @GetMapping("/11")
    public void getAccount() {
        Account account = new Account("vedat", "22");
        Transaction transaction = new WithdrawalTransaction(33.3);
        Transaction transaction2 = new WithdrawalTransaction(1.3);
        account.getTransactions().add(transaction2);
        account.getTransactions().add(transaction);
        accountService.save(account);
    }

}