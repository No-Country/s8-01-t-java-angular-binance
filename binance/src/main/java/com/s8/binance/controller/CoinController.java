package com.s8.binance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<CoinResponseDto>> getAllCoins() {
        List<CoinResponseDto> responseEntity = service.getAll();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoinResponseDto> getCoinById(@PathVariable Long id) {
        CoinResponseDto responseEntity = service.getCoinById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create")
    public ResponseEntity<CoinResponseDto> createCoin(@Valid @RequestBody CoinRequestDto coin) {
        CoinResponseDto responseEntity = service.createCoin(coin);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CoinResponseDto> updateCoin(@Valid @PathVariable Long id,
            @RequestBody CoinRequestDto coinRequestDto) {
        CoinResponseDto responseEntity = service.updateCoin(id, coinRequestDto);
        return ResponseEntity.ok().body(responseEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCoin(@PathVariable Long id) {
        CoinResponseDto responseEntity = service.deleteCoin(id);
        return ResponseEntity.ok().body(responseEntity);
    }
}
