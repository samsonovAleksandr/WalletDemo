package com.example.walletdemo.mapper;

import com.example.walletdemo.dto.WalletDTO;
import com.example.walletdemo.model.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletToWalletDTO {

    public WalletDTO toWalletDTO(Wallet wallet) {
        return WalletDTO.builder()
                .amount(wallet.getAmount())
                .build();
    }

}
