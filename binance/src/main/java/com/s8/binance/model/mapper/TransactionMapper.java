package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Transaction;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
@Component
public class TransactionMapper {

    public TransactionResponseDto fromEntityToDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                // .fkPaymentMethod(transaction.getFkPaymentMethod())
                .transactionType(transaction.getTransactionType())
                .fkPurchaseCoin(transaction.getFkPurchaseCoin())
                .purchaseAmount(transaction.getPurchaseAmount())
                .fkSaleCoin(transaction.getFkSaleCoin())
                .saleAmount(transaction.getSaleAmount())

                .build();
    }

    public Transaction fromDtoToEntity(TransactionRequestDto transactionRequestDto) {
        return Transaction.builder()
                // .fkPaymentMethod(transactionRequestDto.getFkPaymentMethod())
                .transactionType(transactionRequestDto.getTransactionType())
                .fkPurchaseCoin(transactionRequestDto.getFkPurchaseCoin())
                .purchaseAmount(transactionRequestDto.getPurchaseAmount())
                .fkSaleCoin(transactionRequestDto.getFkSaleCoin())
                .saleAmount(transactionRequestDto.getSaleAmount())
                .build();
    }
}
