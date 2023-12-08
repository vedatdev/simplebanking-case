package com.eteration.simplebanking.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@Builder
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp date;
    private Double amount;
    private TransactionType type;
    private String approvalCode;
    public Transaction(Double amount){
        this.date = new Timestamp(System.currentTimeMillis());
        this.amount = amount;
    }

    public Transaction() {

    }

    public abstract void execute(Account account) throws InsufficientBalanceException;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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
