package com.s8.binance.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.s8.binance.util.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto implements Serializable {

    private Long paymentMethodId;

    private TransactionType transactionType;

    private Long purchaseCoinId;

    private BigDecimal purchaseAmount;

    private Long saleCoinId;

    private BigDecimal saleAmount;
    
    private Long walletId;
}