package com.s8.binance.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.service.ITransactionService;
import com.s8.binance.util.enums.TransactionType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
@Api(tags = "Transactions", description = "Management of transactions available in Binance.")
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping("/filters")
    @ApiOperation("Get all transactions according to the specified filters. If no filters are specified, all transactions will be returned.")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionsByFilters(
            @RequestParam(required = false) Long transactionId,
            @RequestParam(required = false) Long paymentMethodId,
            @RequestParam(required = false) TransactionType transactionType,
            @RequestParam(required = false) LocalDate transactionDate,
            @RequestParam(required = false) Long purchaseCoinId,
            @RequestParam(required = false) BigDecimal purchaseAmount,
            @RequestParam(required = false) Long saleCoinId,
            @RequestParam(required = false) BigDecimal saleAmount) {
        List<TransactionResponseDto> transactions = transactionService.getTransactionsByFilters(
                transactionId,
                paymentMethodId,
                transactionType,
                transactionDate,
                purchaseCoinId,
                purchaseAmount,
                saleCoinId,
                saleAmount);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
    }

    @GetMapping("/balance")
    @ApiOperation("Get wallet balance.")
    public ResponseEntity<HashMap<String, BigDecimal>> getWalletBalance(Long walletId) {
        HashMap<String, BigDecimal> responseEntity = transactionService.getWalletBalance(walletId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/deposit")
    @ApiOperation("Create a new deposit.")
    public ResponseEntity<TransactionResponseDto> depositCoin(@Valid @RequestBody DepositRequestDto depositRequestDto) {
        TransactionResponseDto responseEntity = transactionService.createDeposit(depositRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    @PostMapping("/transaction")
    @ApiOperation("Create a new transaction.")
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto responseEntity = transactionService.createTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }
}
