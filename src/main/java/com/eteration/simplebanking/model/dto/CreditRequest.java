package com.eteration.simplebanking.model.dto;


public class CreditRequest {

    private Double amount;

    public CreditRequest(Double amount) {
        this.amount = amount;
    }

    public CreditRequest() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
