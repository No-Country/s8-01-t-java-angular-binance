package com.s8.binance.model.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.PaymentMethod;
import com.s8.binance.model.entity.Transaction;
import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.util.enums.TransactionType;

@Component
public class TransactionMapper {

    public TransactionResponseDto fromEntityToTransactionDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .paymentMethod(transaction.getPaymentMethod())
                .transactionType(transaction.getTransactionType().name())
                .transactionDate(transaction.getTransactionDate())
                .purchaseCoin(transaction.getPurchaseCoin())
                .purchaseAmount(transaction.getPurchaseAmount())
                .saleCoin(transaction.getSaleCoin())
                .saleAmount(transaction.getSaleAmount())
                .walletId(transaction.getWallet().getId())
                .build();
    }

    public Transaction fromTransactionDtoToEntity(TransactionRequestDto transactionRequestDto,
            PaymentMethod paymentMethod, Coin purchaseCoin, Coin saleCoin, Wallet wallet) {
        return Transaction.builder()
                .paymentMethod(paymentMethod)
                .transactionType(transactionRequestDto.getTransactionType())
                .transactionDate(LocalDate.now())
                .purchaseCoin(purchaseCoin)
                .purchaseAmount(transactionRequestDto.getPurchaseAmount())
                .saleCoin(saleCoin)
                .saleAmount(transactionRequestDto.getSaleAmount())
                .wallet(wallet)
                .build();
    }

    public Transaction fromDepositDtoToEntity(DepositRequestDto depositRequestDto, PaymentMethod paymentMethod,
            Coin depositCoin, Wallet wallet) {
        return Transaction.builder()
                .paymentMethod(paymentMethod)
                .transactionType(TransactionType.DEPOSIT)
                .transactionDate(LocalDate.now())
                .purchaseCoin(depositCoin)
                .purchaseAmount(depositRequestDto.getDepositAmount())
                .wallet(wallet)
                .build();
    }
}
