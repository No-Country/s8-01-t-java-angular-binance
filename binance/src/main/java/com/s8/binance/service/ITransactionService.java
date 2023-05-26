package com.s8.binance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.util.enums.TransactionType;

public interface ITransactionService {

    public List<TransactionResponseDto> getAllTransactions();

    public List<TransactionResponseDto> getTransactionsByFilters(Long paymentMethodId, TransactionType transactionType,
            LocalDate transactionDate, Long purchaseCoinId, BigDecimal purchaseAmount, Long saleCoinId,
            BigDecimal saleAmount);

    public TransactionResponseDto getTransactionById(Long id);

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    public void deleteTransaction(Long id);
}
