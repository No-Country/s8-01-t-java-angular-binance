package com.s8.binance.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto implements Serializable {

    private Long id;

    private Long orderId;

    @NotBlank(message = "empty")
    private String type;

    private BigDecimal purchaseAmount;

    private BigDecimal saleAmount;

    private LocalDate transactionDate;
}