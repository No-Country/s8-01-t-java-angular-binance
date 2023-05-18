package com.s8.binance.service.impl;

import com.s8.binance.repository.ITransactionRepository;
import org.springframework.stereotype.Service;

import com.s8.binance.service.ITransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService{
    
    private final ITransactionRepository repository;
}
