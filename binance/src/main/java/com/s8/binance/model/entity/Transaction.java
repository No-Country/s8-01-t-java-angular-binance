package com.s8.binance.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.s8.binance.util.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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

    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_COIN_ID", referencedColumnName = "COIN_ID")
    private Coin purchaseCoin;

    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @ManyToOne
    @JoinColumn(name = "SALE_COIN_ID", referencedColumnName = "COIN_ID")
    private Coin saleCoin;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    // @JsonIgnore
    // @ManyToOne(fetch = EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "WALLET_ID")
    // private Wallet wallet;
}
