package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.mapper.CoinMapper;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;
import com.s8.binance.repository.ICoinRepository;
import com.s8.binance.service.ICoinService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinService implements ICoinService {

    private final ICoinRepository repository;

    private final CoinMapper mapper;

    @Override
    public List<CoinResponseDto> getAll() {
        List<Coin> coins = repository.findAll();
        List<CoinResponseDto> coinResponseDtoList = new ArrayList<>();
        coins.forEach(coin -> {
            CoinResponseDto response = mapper.fromEntityToDto(coin);
            coinResponseDtoList.add(response);
        });
        return coinResponseDtoList;
    }

    @Override
    public CoinResponseDto getCoinById(Long id) {
        Coin coin = repository.findById(id).orElseThrow();
        CoinResponseDto response = mapper.fromEntityToDto(coin);
        return response;
    }

    @Override
    public List<CoinResponseDto> getCoinsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getCoinsByFilters'");
    }

    @Override
    public CoinResponseDto createCoin(CoinRequestDto coinRequestDto) {
        Coin coin = mapper.fromDtoToEntity(coinRequestDto);
        repository.save(coin);
        CoinResponseDto response = mapper.fromEntityToDto(coin);
        return response;
    }

    @Override
    @Transactional
    public CoinResponseDto updateCoin(Long id, CoinRequestDto coinRequestDto) {
        Coin coin = repository.findById(id).orElseThrow();
        Coin updatedCoin = mapper.updateCoin(coin, coinRequestDto);
        repository.save(updatedCoin);
        CoinResponseDto response = mapper.fromEntityToDto(updatedCoin);
        return response;
    }

    @Override
    public CoinResponseDto deleteCoin(Long id) {
        repository.deleteById(id);
        return null;
    }
}