package com.example.walletdemo.controller;


import com.example.walletdemo.dto.WalletDTO;
import com.example.walletdemo.dto.WalletJSON;
import com.example.walletdemo.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService service;

    @PostMapping("/api/v1/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void postWallet(@RequestBody WalletJSON wallet) {
        service.postWallet(wallet);
    }

    @GetMapping("/api/v1/wallets/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public WalletDTO getAmount(@PathVariable UUID uuid) {
        return service.getAmount(uuid);
    }

}
