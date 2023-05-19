package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.request.WalletRequestDto;
import com.s8.binance.model.response.WalletResponseDto;

@Component
public class WalletMapper {

    // public WalletResponseDto fromEntityToDto(Wallet wallet) {
    //     return WalletResponseDto.builder()
    //             .id(wallet.getId())
    //             .assets(wallet.getAssets())
    //             .build(); 
    // }

    // public Wallet fromDtoToEntity(WalletRequestDto walletRequestDto){
    //     return Wallet.builder()
    //             .assets(walletRequestDto.getAssets())
    //             .build();
    // }

    // public Wallet updateWallet(Wallet wallet, WalletRequestDto walletRequestDto){
        
    //     return Wallet;
    // }
}
