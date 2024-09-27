package com.example.walletdemo.exeption;

public class NotWalletJsonException extends RuntimeException {

    public NotWalletJsonException(String message) {
        super(message);
    }
}
