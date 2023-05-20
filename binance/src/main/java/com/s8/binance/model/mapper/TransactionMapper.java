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
                .paymentDate(transaction.getPaymentDate())
                .paymentType(transaction.getPaymentType())
                .build();
    }

    public Transaction fromDtoToEntity(TransactionRequestDto transactionRequestDto) {
        return Transaction.builder()
                .paymentDate(transactionRequestDto.getPaymentDate())
                .paymentType(transactionRequestDto.getPaymentType())
                .build();
    }

    public Transaction updateTransaction(Transaction transaction, TransactionRequestDto transactionRequestDto) {
        transaction.setPaymentDate(transactionRequestDto.getPaymentDate());
        transaction.setPaymentDate(transactionRequestDto.getPaymentDate());
        return transaction;
    }
}
