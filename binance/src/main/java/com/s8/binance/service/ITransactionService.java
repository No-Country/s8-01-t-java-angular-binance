package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;

public interface ITransactionService {

    public List<TransactionResponseDto> getAll();

    public TransactionResponseDto getTransactionById(Long id);

    public List<TransactionResponseDto> getTransactionsByFilters();

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto transactionRequestDto);

    public TransactionResponseDto deleteTransaction(Long id);
}
