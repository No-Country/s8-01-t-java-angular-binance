package com.s8.binance.model.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponseDto implements Serializable {

    private Long id;

    private PaymentMethod paymentMethod;

    private String transactionType;

    private LocalDate transactionDate;

    private Coin depositCoin;

    private BigDecimal depositAmount;
    
    private Long walletId;
}
