package com.s8.binance.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.s8.binance.util.enums.TransactionType;

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
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "SALE_COIN_ID", referencedColumnName = "COIN_ID")
    private Coin saleCoin;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_COIN_ID", referencedColumnName = "COIN_ID")
    private Coin purchaseCoin;

    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;
}