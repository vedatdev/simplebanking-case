package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public abstract class BillPaymentTransaction extends Transaction {

    private String payee;

    public BillPaymentTransaction(String payee, Double amount) {
        super(amount);
        this.payee = payee;
    }

    public BillPaymentTransaction() {
        super();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
}
