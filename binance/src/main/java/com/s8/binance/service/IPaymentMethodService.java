package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.PaymentMethodRequestDto;
import com.s8.binance.model.response.PaymentMethodResponseDto;

public interface IPaymentMethodService {

    public List<PaymentMethodResponseDto> getAllPaymentMethods();

    public PaymentMethodResponseDto getPaymentMethodById(Long id);

    public void createPaymentMethod(PaymentMethodRequestDto paymentMethodRequestDto);

    public void deletePaymentMethod(Long id);
}
