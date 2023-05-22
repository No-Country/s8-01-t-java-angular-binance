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
                .transactionDate(transaction.getTransactionDate())
                .fkPurchaseCoin(transaction.getFkPurchaseCoin())
                .purchaseAmount(transaction.getPurchaseAmount())
                .fkSaleCoin(transaction.getFkSaleCoin())
                .saleAmount(transaction.getSaleAmount())
                .fkWallet(transaction.getFkWallet())
                .build();
    }

    // public List<TransactionResponseDto> fromEntityToDtoList(List<Transaction> transactions) {
    //     List<TransactionResponseDto> dtoList = new ArrayList<>();
    
    //     for (Transaction transaction : transactions) {
    //         TransactionResponseDto dto = TransactionResponseDto.builder()
    //                 .id(transaction.getId())
    //                 // .fkPaymentMethod(transaction.getFkPaymentMethod())
    //                 .transactionType(transaction.getTransactionType())
    //                 .transactionDate(transaction.getTransactionDate())
    //                 .fkPurchaseCoin(transaction.getFkPurchaseCoin())
    //                 .purchaseAmount(transaction.getPurchaseAmount())
    //                 .fkSaleCoin(transaction.getFkSaleCoin())
    //                 .saleAmount(transaction.getSaleAmount())
    //                 .fkWallet(transaction.getFkWallet())
    //                 .build();
    
    //         dtoList.add(dto);
    //     }
    
    //     return dtoList;
    // }

    public Transaction fromDtoToEntity(TransactionRequestDto transactionRequestDto) {
        return Transaction.builder()
                // .fkPaymentMethod(transactionRequestDto.getFkPaymentMethod())
                .transactionType(transactionRequestDto.getTransactionType())
                .transactionDate(transactionRequestDto.getTransactionDate())
                .fkPurchaseCoin(transactionRequestDto.getFkPurchaseCoin())
                .purchaseAmount(transactionRequestDto.getPurchaseAmount())
                .fkSaleCoin(transactionRequestDto.getFkSaleCoin())
                .saleAmount(transactionRequestDto.getSaleAmount())
                .fkWallet(transactionRequestDto.getFkWallet())
                .build();
    }
}
