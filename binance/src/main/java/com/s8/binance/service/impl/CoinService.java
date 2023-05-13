package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.mapper.CoinMapper;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;
import com.s8.binance.repository.ICoinRepository;
import com.s8.binance.security.util.Mensaje;
import com.s8.binance.service.ICoinService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinService implements ICoinService {
    private final ICoinRepository repository;
    private final CoinMapper mapper;
    @Override
    public ResponseEntity<List<CoinResponseDto>> getAll() {
     List<Coin> coins = repository.findAll();
     List<CoinResponseDto> coinResponseDtoList = new ArrayList<>();

     coins.forEach(coin -> {
         CoinResponseDto response = mapper.fromEntityToDto(coin);
         coinResponseDtoList.add(response);
     });

        return new ResponseEntity(coinResponseDtoList,HttpStatus.ACCEPTED);
    }
    @Override
    public ResponseEntity<CoinResponseDto> getCoinById(Long id) {
        Coin coin = repository.findById(id).orElseThrow();
        CoinResponseDto response = mapper.fromEntityToDto(coin);
        return new ResponseEntity<CoinResponseDto>(response, HttpStatus.ACCEPTED);
    }
    @Override
    public List<Coin> getCoinsByFilters() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoinsByFilters'");
    }
    @Override
    public Coin createCoin(CoinRequestDto coinrRequestDto) {

        Coin coin = mapper.fromDtoToEntity(coinrRequestDto);
        repository.save(coin);
        return coin;
    }

    @Override
    public ResponseEntity<CoinResponseDto> updateCoin(Long id, CoinRequestDto coinRequestDto) {
        Coin coin = repository.findById(id).orElseThrow();
        Coin updatedCoin = mapper.updateCoin(coin, coinRequestDto);
        repository.save(updatedCoin);
        CoinResponseDto response = mapper.fromEntityToDto(updatedCoin);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> deleteCoin(Long id) {
      repository.deleteById(id);
      return new ResponseEntity<>(new Mensaje("Coin successfully deleted"), HttpStatus.ACCEPTED);
    }
}
