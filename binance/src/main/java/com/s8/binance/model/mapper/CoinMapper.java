package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;

@Component
public class CoinMapper {

    public CoinResponseDto fromEntityToDto(Coin coin) {
        return CoinResponseDto.builder()
                .id(coin.getId())
                .paymentDate(coin.getPaymentDate())
                .paymentType(coin.getPaymentType())
                .build();
    }

    public Coin fromDtoToEntity(CoinRequestDto coinRequestDto) {
        return Coin.builder()
                .paymentDate(coinRequestDto.getPaymentDate())
                .paymentType(coinRequestDto.getPaymentType())
                .build();
    }

    public Coin updateCoin(Coin coin, CoinRequestDto coinRequestDto) {
        coin.setPaymentDate(coinRequestDto.getPaymentDate());
        coin.setPaymentDate(coinRequestDto.getPaymentDate());
        return coin;
    }
}
