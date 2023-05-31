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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/coins")
@RequiredArgsConstructor
@Api(tags = "Coins", description = "Management of coins available in Binance.")
public class CoinController {

    private final ICoinService coinService;

    @GetMapping
    @ApiOperation("Get all coins.")
    public ResponseEntity<List<CoinResponseDto>> getAllCoins() {
        List<CoinResponseDto> responseEntity = coinService.getAllCoins();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a coin by Id.")
    public ResponseEntity<CoinResponseDto> getCoinById(@PathVariable Long id) {
        CoinResponseDto responseEntity = coinService.getCoinById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create")
    @ApiOperation("Create a new coin.")
    public ResponseEntity<?> createCoin(@Valid @RequestBody CoinRequestDto coin) {
        coinService.createCoin(coin);
        return ResponseEntity.status(HttpStatus.OK).body("Coin successfully created");
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update an existing coin by Id.")
    public ResponseEntity<CoinResponseDto> updateCoin(@Valid @PathVariable Long id,
            @RequestBody CoinRequestDto coinRequestDto) {
        CoinResponseDto responseEntity = coinService.updateCoin(id, coinRequestDto);
        return ResponseEntity.ok().body(responseEntity);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an existing coin by Id.")
    public ResponseEntity<?> deleteCoin(@PathVariable Long id) {
        coinService.deleteCoin(id);
        return ResponseEntity.ok().body("Coin successfully deleted");
    }
}
