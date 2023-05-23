package com.s8.binance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.WalletRequestDto;
import com.s8.binance.model.response.WalletResponseDto;
import com.s8.binance.service.IWalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final IWalletService service;

    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        List<WalletResponseDto> responseEntity = service.getAll();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable Long id) {
        WalletResponseDto responseEntity = service.getWalletById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

//    @PostMapping("/create")
//    public ResponseEntity<WalletResponseDto> createWallet(@Valid @RequestBody WalletRequestDto wallet) {
//        WalletResponseDto responseEntity = service.createWallet(wallet);
//        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
//    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<WalletResponseDto> updateWallet(@Valid @PathVariable Long id,
    //         @RequestBody WalletRequestDto walletRequestDto) {
    //     WalletResponseDto responseEntity = service.updateWallet(id, walletRequestDto);
    //     return ResponseEntity.ok().body(responseEntity);
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        WalletResponseDto responseEntity = service.deleteWallet(id);
        return ResponseEntity.ok().body(responseEntity);
    }
}
