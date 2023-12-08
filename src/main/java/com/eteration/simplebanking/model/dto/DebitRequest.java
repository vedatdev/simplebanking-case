package com.eteration.simplebanking.model.dto;


public class DebitRequest {
    private Double amount;

    public DebitRequest(Double amount) {
        this.amount = amount;
    }

    public DebitRequest() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
