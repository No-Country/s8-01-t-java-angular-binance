package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

    private final IPaymentMethodRepository repository;

    private final PaymentMethodMapper mapper;

    @Override
    public List<PaymentMethodResponseDto> getAll() {
        List<PaymentMethod> paymentMethods = repository.findAll();
        List<PaymentMethodResponseDto> paymentMethodResponseDtoList = new ArrayList<>();
        paymentMethods.forEach(paymentMethod -> {
            PaymentMethodResponseDto response = mapper.fromEntityToDto(paymentMethod);
            paymentMethodResponseDtoList.add(response);
        });
        return paymentMethodResponseDtoList;
    }

    @Override
    public PaymentMethodResponseDto getPaymentMethodById(Long id) {
        PaymentMethod paymentMethod = repository.findById(id).orElseThrow();
        PaymentMethodResponseDto response = mapper.fromEntityToDto(paymentMethod);
        return response;
    }

    @Override
    public List<PaymentMethodResponseDto> getPaymentMethodsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentMethodsByFilters'");
    }

    @Override
    public PaymentMethodResponseDto createPaymentMethod(PaymentMethodRequestDto paymentMethodRequestDto) {
        PaymentMethod paymentMethod = mapper.fromDtoToEntity(paymentMethodRequestDto);
        repository.save(paymentMethod);
        PaymentMethodResponseDto response = mapper.fromEntityToDto(paymentMethod);
        return response;
    }

    @Override
    @Transactional
    public PaymentMethodResponseDto updatePaymentMethod(Long id, PaymentMethodRequestDto paymentMethodRequestDto) {
        PaymentMethod paymentMethod = repository.findById(id).orElseThrow();
        PaymentMethod updatedPaymentMethod = mapper.updatePaymentMethod(paymentMethod, paymentMethodRequestDto);
        repository.save(updatedPaymentMethod);
        PaymentMethodResponseDto response = mapper.fromEntityToDto(updatedPaymentMethod);
        return response;
    }

    @Override
    public PaymentMethodResponseDto deletePaymentMethod(Long id) {
        repository.deleteById(id);
        return null;
    }
}
