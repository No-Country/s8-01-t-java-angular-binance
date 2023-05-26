package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.PaymentMethod;
import com.s8.binance.model.entity.Transaction;
import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.mapper.TransactionMapper;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.repository.ICoinRepository;
import com.s8.binance.repository.IPaymentMethodRepository;
import com.s8.binance.repository.ITransactionRepository;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.service.ITransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionMapper transactionMapper;

    private final ITransactionRepository transactionRepository;

    private final IPaymentMethodRepository paymentMethodRepository;

    private final ICoinRepository coinRepository;

    private final IWalletRepository walletRepository;

    @Override
    public List<TransactionResponseDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
        transactions.forEach(transaction -> {
            TransactionResponseDto response = transactionMapper.fromEntityToDto(transaction);
            transactionResponseDtoList.add(response);
        });
        return transactionResponseDtoList;
    }

    @Override
    public List<TransactionResponseDto> getTransactionsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getTransactionsByFilters'");
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        TransactionResponseDto response = transactionMapper.fromEntityToDto(transaction);
        return response;
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        
        PaymentMethod paymentMethod = paymentMethodRepository.findById(transactionRequestDto.getPaymentMethodId()).orElseThrow();
        Coin purchaseCoin = coinRepository.findById(transactionRequestDto.getPurchaseCoinId()).orElseThrow();
        Coin saleCoin = coinRepository.findById(transactionRequestDto.getSaleCoinId()).orElseThrow();
        Wallet wallet = new Wallet(); // walletRepository.findById(transactionRequestDto.getWalletId()).orElseThrow();
        
        Transaction transaction = transactionMapper.fromDtoToEntity(transactionRequestDto, paymentMethod, purchaseCoin, saleCoin, wallet);
        transactionRepository.save(transaction);

        return transactionMapper.fromEntityToDto(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
