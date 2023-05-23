package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.PaymentMethod;
import com.s8.binance.model.request.PaymentMethodRequestDto;
import com.s8.binance.model.response.PaymentMethodResponseDto;

@Component
public class PaymentMethodMapper {

    public PaymentMethodResponseDto fromEntityToDto(PaymentMethod paymentMethod) {
        return PaymentMethodResponseDto.builder()
                .id(paymentMethod.getId())
                .paymentType(paymentMethod.getPaymentType())
                .build();
    }

    public PaymentMethod fromDtoToEntity(PaymentMethodRequestDto paymentMethodRequestDto) {
        return PaymentMethod.builder()
                .paymentType(paymentMethodRequestDto.getPaymentType())
                .build();
    }

 
}
