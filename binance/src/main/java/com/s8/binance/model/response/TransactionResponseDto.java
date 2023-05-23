package com.s8.binance.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private Long id;

    // private Long fkPaymentMethod;

    private String transactionType;

    private Long fkPurchaseCoin;

    private BigDecimal purchaseAmount;

    private Long fkSaleCoin;

    private BigDecimal saleAmount;

    private Long fkWallet;
}