package com.s8.binance.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.DepositResponseDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.service.ITransactionService;
import com.s8.binance.util.enums.TransactionType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transactions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Api(tags = "Transactions", description = "Management of available transactions on Binance.")
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping("/filters")
    @ApiOperation("Retrieve all transactions based on the specified filters. If no filters are specified, all transactions will be returned.")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionsByFilters(
            @RequestParam(required = false) Long paymentMethodId,
            @RequestParam(required = false) TransactionType transactionType,
            @RequestParam(required = false) LocalDate transactionDate,
            @RequestParam(required = false) Long purchaseCoinId,
            @RequestParam(required = false) BigDecimal purchaseAmount,
            @RequestParam(required = false) Long saleCoinId,
            @RequestParam(required = false) BigDecimal saleAmount,
            @RequestParam(required = false) Long walletId) {
        List<TransactionResponseDto> transactions = transactionService.getTransactionsByFilters(paymentMethodId,
                transactionType, transactionDate, purchaseCoinId, purchaseAmount, saleCoinId, saleAmount, walletId);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a transaction by Id.")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        TransactionResponseDto responseEntity = transactionService.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @GetMapping("/balance")
    @ApiOperation("Get balance by wallet Id.")
    public ResponseEntity<HashMap<String, BigDecimal>> getWalletBalance(Long walletId) {
        HashMap<String, BigDecimal> responseEntity = transactionService.getWalletBalance(walletId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/deposit")
    @ApiOperation("Create a new deposit.")
    public ResponseEntity<DepositResponseDto> createDeposit(@Valid @RequestBody DepositRequestDto depositRequestDto) {
        DepositResponseDto responseEntity = transactionService.createDeposit(depositRequestDto);
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
