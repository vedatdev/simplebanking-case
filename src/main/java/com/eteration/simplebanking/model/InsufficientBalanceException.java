package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class InsufficientBalanceException extends Exception {
    private final String message;

    public InsufficientBalanceException() {
        this.message = "Insufficient balance for this operation.";
    }

    public InsufficientBalanceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
