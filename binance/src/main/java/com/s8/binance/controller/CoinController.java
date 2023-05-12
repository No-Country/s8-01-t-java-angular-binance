package com.s8.binance.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.CoinResponseDto;
import com.s8.binance.service.ICoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/coins")
@RequiredArgsConstructor
public class CoinController {
    
    private final ICoinService service;

    @GetMapping
    public ResponseEntity<List<CoinResponseDto>> getAllCoins(){
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) service.getAll();
        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoinResponseDto> getCoinById(@PathVariable Long id){
         ResponseEntity<?> responseEntity = (ResponseEntity<?>) service.getCoinById(id);
        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }
   
    @PostMapping("/create")
    public ResponseEntity<Coin> createCoin(@RequestBody CoinRequestDto coin){
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) service.createCoin(coin);
        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<CoinResponseDto> updateCoin(@PathVariable Long id, @RequestBody CoinRequestDto coinRequestDto){
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) service.updateCoin(id, coinRequestDto);
        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Coin> deleteCoin(@PathVariable Long id){
        ResponseEntity<?> responseEntity = service.deleteCoin(id);
        return new ResponseEntity(responseEntity.getBody(),responseEntity.getStatusCode());
    }
}
