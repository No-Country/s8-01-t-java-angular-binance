package com.s8.binance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.DepositResponseDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.util.enums.TransactionType;

public interface ITransactionService {

    public List<TransactionResponseDto> getTransactionsByFilters(
            Long paymentMethodId,
            TransactionType transactionType,
            LocalDate transactionDate,
            Long purchaseCoinId,
            BigDecimal purchaseAmount,
            Long saleCoinId,
            BigDecimal saleAmount,
            Long walletId);

    public TransactionResponseDto getTransactionById(Long id);

    public HashMap<String, BigDecimal> getWalletBalance(Long walletId);

    public DepositResponseDto createDeposit(DepositRequestDto depositRequestDto);

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    public boolean checkBalance(Long id, BigDecimal amount);
}
