package com.eteration.simplebanking.model.dto;

import com.eteration.simplebanking.model.TransactionType;

import java.util.Date;

public class TransactionDto {

    private Date date;
    private Double amount;
    private TransactionType type;
    private String approvalCode;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
