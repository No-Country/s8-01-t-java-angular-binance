package com.s8.binance.model.request;

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
public class TransactionRequestDto implements Serializable {

    @NotBlank(message = "empty")
    private String type;

    private BigDecimal purchaseAmount;

    private BigDecimal saleAmount;

    private LocalDate transactionDate;
}