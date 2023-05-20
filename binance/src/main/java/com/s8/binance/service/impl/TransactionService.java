package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Transaction;
import com.s8.binance.model.mapper.TransactionMapper;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.repository.ITransactionRepository;
import com.s8.binance.service.ITransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository repository;

    private final TransactionMapper mapper;

    @Override
    public List<TransactionResponseDto> getAll() {
        List<Transaction> transactions = repository.findAll();
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
        transactions.forEach(transaction -> {
            TransactionResponseDto response = mapper.fromEntityToDto(transaction);
            transactionResponseDtoList.add(response);
        });
        return transactionResponseDtoList;
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = repository.findById(id).orElseThrow();
        TransactionResponseDto response = mapper.fromEntityToDto(transaction);
        return response;
    }

    @Override
    public List<TransactionResponseDto> getTransactionsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getTransactionsByFilters'");
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        Transaction transaction = mapper.fromDtoToEntity(transactionRequestDto);
        repository.save(transaction);
        TransactionResponseDto response = mapper.fromEntityToDto(transaction);
        return response;
    }

    @Override
    @Transactional
    public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto transactionRequestDto) {
        Transaction transaction = repository.findById(id).orElseThrow();
        Transaction updatedTransaction = mapper.updateTransaction(transaction, transactionRequestDto);
        repository.save(updatedTransaction);
        TransactionResponseDto response = mapper.fromEntityToDto(updatedTransaction);
        return response;
    }

    @Override
    public TransactionResponseDto deleteTransaction(Long id) {
        repository.deleteById(id);
        return null;
    }
}
