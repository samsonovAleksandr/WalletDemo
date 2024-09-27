package com.example.walletdemo.service;

import com.example.walletdemo.dto.WalletDTO;
import com.example.walletdemo.dto.WalletJSON;
import com.example.walletdemo.enums.WalletOperation;
import com.example.walletdemo.exeption.NotEnoughFundsException;
import com.example.walletdemo.exeption.NotWalletJsonException;
import com.example.walletdemo.exeption.WalletNotFoundException;
import com.example.walletdemo.mapper.WalletToWalletDTO;
import com.example.walletdemo.model.Wallet;
import com.example.walletdemo.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    private final WalletToWalletDTO walletMapper;

    public WalletServiceImpl(WalletRepository repository, WalletToWalletDTO walletMapper) {
        this.repository = repository;
        this.walletMapper = walletMapper;
    }

    @Override
    public void postWallet(WalletJSON wallet) {
        if (wallet.getOperationType() != WalletOperation.DEPOSIT
                && wallet.getOperationType() != WalletOperation.WITHDRAW) {
            throw new NotWalletJsonException("Неверно указана операция с кошельком.");
        }
        repository.findById(wallet.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Кашелька с таким UUID не найдено"));
        Wallet wl = repository.getReferenceById(wallet.getWalletId());
        BigDecimal amount = wallet.getAmount();

        if (amount.compareTo(wl.getAmount()) > 0) {
            throw new NotEnoughFundsException("Недостаточно средств на балансе.");
        }
        switch (wallet.getOperationType()) {
            case DEPOSIT:
                BigDecimal amountWalletResult = amount.add(wl.getAmount()).setScale(2, RoundingMode.HALF_UP);
                wl.setAmount(amountWalletResult);
                repository.save(wl);
                break;
            case WITHDRAW:
                BigDecimal amountWallet = amount.subtract(wl.getAmount()).setScale(2, RoundingMode.HALF_UP);
                wl.setAmount(amountWallet);
                repository.save(wl);
                break;
        }
    }

    @Override
    public WalletDTO getAmount(UUID uuid) {
        repository.findById(uuid).orElseThrow(() -> new WalletNotFoundException("Кашелька с таким UUID не найдено"));
        Wallet wallet = repository.getReferenceById(uuid);
        return walletMapper.toWalletDTO(wallet);
    }
}
