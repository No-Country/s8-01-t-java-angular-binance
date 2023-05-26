package com.s8.binance.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.PaymentMethod;

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

    private PaymentMethod paymentMethod;

    private String transactionType;

    private LocalDate transactionDate;

    private Coin purchaseCoin;

    private BigDecimal purchaseAmount;

    private Coin saleCoin;

    private BigDecimal saleAmount;
    
    private Long walletId;
}