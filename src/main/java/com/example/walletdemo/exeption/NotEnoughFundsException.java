package com.example.walletdemo.exeption;

public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException(String message) {
        super(message);
    }
}
