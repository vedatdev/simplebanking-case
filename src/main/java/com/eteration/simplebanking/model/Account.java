package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "accounts")
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String owner;
    private String accountNumber;
    private Double balance;
    private Timestamp createDate;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.execute(this);
    }

    public void debit(Double amount) throws InsufficientBalanceException {
        if (balance >= amount){
            this.balance = balance - amount;
        } else {
            throw new InsufficientBalanceException();
        }
    }

    public void credit(Double amount) {
        this.balance = balance + amount;
    }
}
