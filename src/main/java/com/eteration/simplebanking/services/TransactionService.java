package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.stereotype.Service;
import com.eteration.simplebanking.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
