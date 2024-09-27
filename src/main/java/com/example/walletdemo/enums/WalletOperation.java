package com.example.walletdemo.enums;

public enum WalletOperation {
    DEPOSIT, WITHDRAW;

    public static WalletOperation fromString(String state) {
        return WalletOperation.valueOf(state);
    }
}
