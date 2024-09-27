package com.example.walletdemo.service;

import com.example.walletdemo.dto.WalletDTO;
import com.example.walletdemo.dto.WalletJSON;

import java.util.UUID;

public interface WalletService {

    void postWallet(WalletJSON wallet);

    WalletDTO getAmount(UUID uuid);
}
