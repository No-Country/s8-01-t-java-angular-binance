package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;

@Component
public class CoinMapper {
    
    public CoinResponseDto fromEntityToDto(Coin coin){
        return CoinResponseDto.builder()
        .id(coin.getId())
        .name(coin.getName())
        .currentValue(coin.getCurrentValue())
        .build();
    }
    
    public Coin fromDtoToEntity(CoinRequestDto coinRequestDto){
        return Coin.builder()
        .name(coinRequestDto.getName())
        .currentValue(coinRequestDto.getCurrentValue())
        .build();
    }

    public Coin updateCoin(Coin coin, CoinRequestDto coinRequestDto){
        coin.setName(coinRequestDto.getName());
        coin.setCurrentValue(coinRequestDto.getCurrentValue());
        return coin;
    }
}
