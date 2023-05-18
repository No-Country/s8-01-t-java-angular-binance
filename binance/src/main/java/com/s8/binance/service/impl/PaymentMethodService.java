package com.s8.binance.service.impl;

import com.s8.binance.repository.IPaymentMethodRepository;
import org.springframework.stereotype.Service;

import com.s8.binance.service.IPaymentMethodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodService implements IPaymentMethodService{
    
    private final IPaymentMethodRepository repository;
}
