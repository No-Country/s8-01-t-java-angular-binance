package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.PaymentMethod;
import com.s8.binance.model.mapper.PaymentMethodMapper;
import com.s8.binance.model.request.PaymentMethodRequestDto;
import com.s8.binance.model.response.PaymentMethodResponseDto;
import com.s8.binance.repository.IPaymentMethodRepository;
import com.s8.binance.service.IPaymentMethodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodService implements IPaymentMethodService {

    private final PaymentMethodMapper paymentMethodMapper;

    private final IPaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethodResponseDto> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        List<PaymentMethodResponseDto> paymentMethodResponseDtoList = new ArrayList<>();
        paymentMethods.forEach(paymentMethod -> {
            PaymentMethodResponseDto response = paymentMethodMapper.fromEntityToDto(paymentMethod);
            paymentMethodResponseDtoList.add(response);
        });
        return paymentMethodResponseDtoList;
    }

    @Override
    public PaymentMethodResponseDto getPaymentMethodById(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow();
        PaymentMethodResponseDto response = paymentMethodMapper.fromEntityToDto(paymentMethod);
        return response;
    }

    @Override
    public void createPaymentMethod(PaymentMethodRequestDto paymentMethodRequestDto) {
        PaymentMethod paymentMethod = paymentMethodMapper.fromDtoToEntity(paymentMethodRequestDto);
        paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
