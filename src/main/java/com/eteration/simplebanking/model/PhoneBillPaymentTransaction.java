package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends BillPaymentTransaction{

    private String phoneNumber;

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, Double amount) {
        super(payee,amount);
        this.phoneNumber = phoneNumber;
        this.setType(TransactionType.BILL);
    }

    public PhoneBillPaymentTransaction() {
        super();
    }

    @Override
    public void execute(Account account) throws InsufficientBalanceException {
        if (account.getBalance() >= this.getAmount()) {
            account.setBalance(account.getBalance() - this.getAmount());
        } else {
            throw new InsufficientBalanceException();
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
