package com.example.walletdemo.repository;

import com.example.walletdemo.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Override
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Wallet> findById(UUID uuid);
}
