package com.s8.binance.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;

public interface ICoinService {
    
    public ResponseEntity<List<CoinResponseDto>> getAll();
    
    public ResponseEntity<CoinResponseDto> getCoinById(Long id);

    public List<Coin> getCoinsByFilters();

    public ResponseEntity<?> createCoin(CoinRequestDto coinRequestDto);

    public ResponseEntity<CoinResponseDto> updateCoin(Long id, CoinRequestDto coinRequestDto);

    public ResponseEntity<?> deleteCoin(Long id);

}
