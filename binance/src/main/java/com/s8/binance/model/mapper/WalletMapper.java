package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.request.WalletRequestDto;
import com.s8.binance.model.response.WalletResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WalletMapper {


    public WalletResponseDto fromEntityToDto(Wallet wallet) {
        return WalletResponseDto.builder()
                .id(wallet.getId())
                .transactions(wallet.getTransactions())
                .build();
    }

    public Wallet fromDtoToEntity(WalletRequestDto walletRequestDto) {
        return Wallet.builder()
                .id(walletRequestDto.getId())
                .build();
    }
}
