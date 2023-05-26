package com.s8.binance.model.response;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodResponseDto implements Serializable {

    private Long id;

    @NotBlank(message = "empty")
    private String paymentType;
}