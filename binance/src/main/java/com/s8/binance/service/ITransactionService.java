package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;

public interface ITransactionService {

    public List<TransactionResponseDto> getAllTransactions();

    public List<TransactionResponseDto> getTransactionsByFilters();

    public TransactionResponseDto getTransactionById(Long id);

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    public void deleteTransaction(Long id);
}
