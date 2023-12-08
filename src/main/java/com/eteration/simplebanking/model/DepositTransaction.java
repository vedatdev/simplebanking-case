package com.eteration.simplebanking.model;


import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorValue("Deposit")
@SuperBuilder
public class DepositTransaction extends Transaction  {


    public DepositTransaction(Double amount) {
        super(amount);
        this.setType(TransactionType.DEPOSIT);
    }

    public DepositTransaction() {
        super();
    }

    @Override
    public void execute(Account account) {
        account.setBalance(account.getBalance() + this.getAmount());
        account.getTransactions().add(this);
    }
}
