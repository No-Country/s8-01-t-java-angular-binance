package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.PaymentMethodRequestDto;
import com.s8.binance.model.response.PaymentMethodResponseDto;

public interface IPaymentMethodService {

    public List<PaymentMethodResponseDto> getAll();

    public PaymentMethodResponseDto getPaymentMethodById(Long id);

    public List<PaymentMethodResponseDto> getPaymentMethodsByFilters();

    public PaymentMethodResponseDto createPaymentMethod(PaymentMethodRequestDto paymentMethodRequestDto);

    public PaymentMethodResponseDto updatePaymentMethod(Long id, PaymentMethodRequestDto paymentMethodRequestDto);

    public PaymentMethodResponseDto deletePaymentMethod(Long id);
}
