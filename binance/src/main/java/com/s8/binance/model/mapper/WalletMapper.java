package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.request.WalletRequestDto;
import com.s8.binance.model.response.WalletResponseDto;

@Component
public class WalletMapper {

    public WalletResponseDto fromEntityToDto(Wallet wallet) {
        return WalletResponseDto.builder()
                .id(wallet.getId())
                .paymentDate(wallet.getPaymentDate())
                .paymentType(wallet.getPaymentType())
                .build();
    }

    public Wallet fromDtoToEntity(WalletRequestDto walletRequestDto) {
        return Wallet.builder()
                .paymentDate(walletRequestDto.getPaymentDate())
                .paymentType(walletRequestDto.getPaymentType())
                .build();
    }

    public Wallet updateWallet(Wallet wallet, WalletRequestDto walletRequestDto) {
        wallet.setPaymentDate(walletRequestDto.getPaymentDate());
        wallet.setPaymentDate(walletRequestDto.getPaymentDate());
        return wallet;
    }
}
