package com.eteration.simplebanking.model;


import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue("Withdrawal")
@SuperBuilder
public class WithdrawalTransaction extends Transaction {


    public WithdrawalTransaction(Double amount) {
        super(amount);
        this.setType(TransactionType.WITHDRAWAL);
    }

    public WithdrawalTransaction() {
        super();
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        if (account.getBalance() >= this.getAmount()) {
            account.setBalance(account.getBalance() - this.getAmount());
            account.getTransactions().add(this);
        } else {
            throw new InsufficientBalanceException();
        }
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAWAL;
    }


}


