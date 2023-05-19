package com.s8.binance.model.response;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Value;

/**
 * DTO for {@link com.s8.binance.model.entity.PaymentMethod}
 */
@Value
public class PaymentMethodResponseDto implements Serializable {
    Long id;
    @NotBlank(message = "Empty")
    String paymentType;
    LocalDate paymentDate;
}