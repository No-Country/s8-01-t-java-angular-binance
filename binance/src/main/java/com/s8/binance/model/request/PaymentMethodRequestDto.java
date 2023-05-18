package com.s8.binance.model.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.s8.binance.model.entity.PaymentMethod}
 */
@Value
public class PaymentMethodRequestDto implements Serializable {
    @NotBlank(message = "Empty")
    String paymentType;
    LocalDate paymentDate;
}