package com.s8.binance.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.service.ITransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService service;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> responseEntity = service.getAll();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        TransactionResponseDto responseEntity = service.getTransactionById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDto> createTransaction(@Valid @RequestBody TransactionRequestDto transaction, Long id) {
        TransactionResponseDto responseEntity = service.createTransaction(transaction,id);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<TransactionResponseDto> updateTransaction(@Valid @PathVariable Long id,
    //         @RequestBody TransactionRequestDto transactionRequestDto) {
    //     TransactionResponseDto responseEntity = service.updateTransaction(id, transactionRequestDto);
    //     return ResponseEntity.ok().body(responseEntity);
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        TransactionResponseDto responseEntity = service.deleteTransaction(id);
        return ResponseEntity.ok().body(responseEntity);
    }
}
