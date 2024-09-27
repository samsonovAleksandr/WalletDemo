package com.example.walletdemo.repository;

import com.example.walletdemo.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
