package com.s8.binance.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

//    @Column(name = "PAYMENT_METHOD")
//    private PaymentMethod paymentMethod;

    @Column(name = "TYPE")
    @NotBlank(message = "Empty")
    private String type;

//    @Column(name = "PURCHASE_COIN")
//    private Coin purchaseCoinId;

    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

//    @Column(name = "SALE_COIN")
//    private Coin saleCoinId;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;
}
