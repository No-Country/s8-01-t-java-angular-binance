package com.s8.binance.model.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.s8.binance.model.entity.Transaction}
 */
@Value
public class TransactionRequestDto implements Serializable {
    @NotBlank(message = "Empty")
    String type;
    BigDecimal purchaseAmount;
    BigDecimal saleAmount;
    LocalDate transactionDate;
}