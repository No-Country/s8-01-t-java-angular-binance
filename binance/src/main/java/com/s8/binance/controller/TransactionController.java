package com.s8.binance.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "Transactions", description = "Management of transactions available in Binance. It allows creating, modifying, and deleting transactions, as well as obtaining detailed information about them")
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping
    @ApiOperation("Get all transactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> responseEntity = transactionService.getAllTransactions();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/filters")
    @ApiOperation("Get all transactions according to the specified filters")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionsByFilters(
            @RequestParam(required = false) Long paymentMethodId,
            @RequestParam(required = false) TransactionType transactionType,
            @RequestParam(required = false) LocalDate transactionDate,
            @RequestParam(required = false) Long purchaseCoinId,
            @RequestParam(required = false) BigDecimal purchaseAmount,
            @RequestParam(required = false) Long saleCoinId,
            @RequestParam(required = false) BigDecimal saleAmount) {
        List<TransactionResponseDto> transactions = transactionService.getTransactionsByFilters(paymentMethodId,
                transactionType,
                transactionDate, purchaseCoinId, purchaseAmount, saleCoinId, saleAmount);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a transaction by Id")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        TransactionResponseDto responseEntity = transactionService.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create")
    @ApiOperation("Create a new transaction")
    public ResponseEntity<TransactionResponseDto> createTransaction(
            @Valid @RequestBody TransactionRequestDto transaction) {
        TransactionResponseDto responseEntity = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an existing transaction by Id")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().body("Transaction successfully deleted");
    }
}
