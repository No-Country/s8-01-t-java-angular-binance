package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;

public interface ICoinService {
    public List<CoinResponseDto> getAll();
    public CoinResponseDto getCoinById(Long id);
    public Coin getCoinsByFilters();
    public Coin createCoin(CoinRequestDto coinRequestDto);
    public CoinResponseDto updateCoin(Long id, CoinRequestDto coinRequestDto);
    public CoinResponseDto deleteCoin(Long id);
}
