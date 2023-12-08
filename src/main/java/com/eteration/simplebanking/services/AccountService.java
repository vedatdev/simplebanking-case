package com.eteration.simplebanking.services;


import com.eteration.simplebanking.mapper.RequestResponseMapper;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.model.dto.*;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RequestResponseMapper requestResponseMapper;

    public AccountService(AccountRepository accountRepository, RequestResponseMapper requestResponseMapper) {
        this.accountRepository = accountRepository;
        this.requestResponseMapper = requestResponseMapper;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public AccountResponse getByAccountNumber(String accountNumber) {
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        return requestResponseMapper.accountToResponse(account);
    }

    public DebitResponse debit(DebitRequest debitRequest, String accountNumber) throws InsufficientBalanceException {

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);

        if (account.getBalance() >= debitRequest.getAmount()){
            account.setBalance(account.getBalance()-debitRequest.getAmount());
            Transaction transaction = new WithdrawalTransaction(debitRequest.getAmount());
            transaction.setApprovalCode(UUID.randomUUID().toString());
            account.getTransactions().add(transaction);
            accountRepository.save(account);
            return new DebitResponse("OK", UUID.randomUUID().toString());
        } else {
            throw new InsufficientBalanceException();
        }
    }

    public CreditResponse credit(CreditRequest creditRequest, String accountNumber) {

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        Transaction transaction = new DepositTransaction(creditRequest.getAmount());
        transaction.setApprovalCode(UUID.randomUUID().toString());
        account.setBalance(account.getBalance() + creditRequest.getAmount());
        account.getTransactions().add(transaction);
        accountRepository.save(account);
        return new CreditResponse("OK",transaction.getApprovalCode());
    }
}
