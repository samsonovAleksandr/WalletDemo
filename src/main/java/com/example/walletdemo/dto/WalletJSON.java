package com.example.walletdemo.dto;

import com.example.walletdemo.enums.WalletOperation;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Builder
public class WalletJSON {

    private UUID walletId;


    private WalletOperation operationType;

    @Positive
    private BigDecimal amount;
}
