package com.example.walletdemo.controller;

import com.example.walletdemo.dto.WalletDTO;
import com.example.walletdemo.dto.WalletJSON;
import com.example.walletdemo.enums.WalletOperation;
import com.example.walletdemo.model.Wallet;
import com.example.walletdemo.repository.WalletRepository;
import com.example.walletdemo.service.WalletServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    WalletRepository repository;

    private WalletDTO walletDTO;
    private Wallet wallet;
    private WalletJSON walletJSON;


    @MockBean
    private WalletServiceImpl service;

    @BeforeEach
    public void setUp() throws Exception {
        wallet = new Wallet(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
                , new BigDecimal("10000"));

        walletDTO = WalletDTO.builder()
                .amount(wallet.getAmount())
                .build();
        walletJSON = WalletJSON.builder()
                .walletId(UUID.fromString("0000bc99-9c0b-4ef8-bb6d-6bb7bd380a11"))
                .operationType(WalletOperation.DEPOSIT)
                .amount(new BigDecimal("10000"))
                .build();
    }

    @Test
    void shouldFindWalletById() throws Exception {
        UUID uuid = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
        when(service.getAmount(uuid)).thenReturn(walletDTO);

        mockMvc.perform(get("/api/v1/wallets/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testPostWallet() throws Exception {
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(walletJSON)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
