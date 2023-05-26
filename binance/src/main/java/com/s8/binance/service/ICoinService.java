package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;

public interface ICoinService {

    public List<CoinResponseDto> getAllCoins();

    public CoinResponseDto getCoinById(Long id);

    public void createCoin(CoinRequestDto coinRequestDto);

    public CoinResponseDto updateCoin(Long id, CoinRequestDto coinRequestDto);

    public void deleteCoin(Long id);
}
