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
                .paymentDate(paymentMethod.getPaymentDate())
                .paymentType(paymentMethod.getPaymentType())
                .build();
    }

    public PaymentMethod fromDtoToEntity(PaymentMethodRequestDto paymentMethodRequestDto) {
        return PaymentMethod.builder()
                .paymentDate(paymentMethodRequestDto.getPaymentDate())
                .paymentType(paymentMethodRequestDto.getPaymentType())
                .build();
    }

    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod,
            PaymentMethodRequestDto paymentMethodRequestDto) {
        paymentMethod.setPaymentDate(paymentMethodRequestDto.getPaymentDate());
        paymentMethod.setPaymentDate(paymentMethodRequestDto.getPaymentDate());
        return paymentMethod;
    }
}
