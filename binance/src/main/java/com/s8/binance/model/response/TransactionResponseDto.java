package com.s8.binance.model.response;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.s8.binance.model.entity.Transaction}
 */
@Value
public class TransactionResponseDto implements Serializable {
    Long id;
    Long orderId;
    @NotBlank(message = "Empty")
    String type;
    BigDecimal purchaseAmount;
    BigDecimal saleAmount;
    LocalDate transactionDate;
}