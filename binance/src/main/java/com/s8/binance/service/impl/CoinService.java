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

    private final CoinMapper coinMapper;

    private final ICoinRepository coinRepository;

    @Override
    public List<CoinResponseDto> getAllCoins() {
        List<Coin> coins = coinRepository.findAll();
        List<CoinResponseDto> coinResponseDtoList = new ArrayList<>();
        coins.forEach(coin -> {
            CoinResponseDto response = coinMapper.fromEntityToDto(coin);
            coinResponseDtoList.add(response);
        });
        return coinResponseDtoList;
    }

    @Override
    public CoinResponseDto getCoinById(Long id) {
        Coin coin = coinRepository.findById(id).orElseThrow();
        CoinResponseDto response = coinMapper.fromEntityToDto(coin);
        return response;
    }

    @Override
    public void createCoin(CoinRequestDto coinRequestDto) {
        Coin coin = coinMapper.fromDtoToEntity(coinRequestDto);
        coinRepository.save(coin);
    }

    @Override
    @Transactional
    public CoinResponseDto updateCoin(Long id, CoinRequestDto coinRequestDto) {
        Coin coin = coinRepository.findById(id).orElseThrow();
        Coin updatedCoin = coinMapper.updateCoin(coin, coinRequestDto);
        coinRepository.save(updatedCoin);
        CoinResponseDto response = coinMapper.fromEntityToDto(updatedCoin);
        return response;
    }

    @Override
    public void deleteCoin(Long id) {
        coinRepository.deleteById(id);
    }
}
