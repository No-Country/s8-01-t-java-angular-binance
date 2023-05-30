package com.s8.binance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.util.enums.TransactionType;

public interface ITransactionService {

    public List<TransactionResponseDto> getTransactionsByFilters(
        Long transactionId,
        Long paymentMethodId,
        TransactionType transactionType,
        LocalDate transactionDate,
        Long purchaseCoinId,
        BigDecimal purchaseAmount,
        Long saleCoinId,
        BigDecimal saleAmount);

    public HashMap<String, BigDecimal> getWalletBalance(Long walletId);

    public TransactionResponseDto createDeposit(DepositRequestDto depositRequestDto);

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);
}
