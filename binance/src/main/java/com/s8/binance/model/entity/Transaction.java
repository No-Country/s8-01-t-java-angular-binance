package com.s8.binance.model.entity;

import static javax.persistence.FetchType.EAGER;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // @Column(name = "FK_PAYMENT_METHOD")
    // private Long fkPaymentMethod;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "FK_PAYMENT_METHOD", referencedColumnName = "ID_PAYMENT_METHOD", insertable = false, updatable = false)
    // private PaymentMethod paymentMethod;

    @Column(name = "TRANSACTION_TYPE")
    @NotBlank(message = "empty")
    private String transactionType;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @Column(name = "FK_PURCHASE_COIN")
    private Long fkPurchaseCoin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PURCHASE_COIN", referencedColumnName = "ID_COIN", insertable = false, updatable = false)
    private Coin purchaseCoin;

    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;

    @Column(name = "FK_SALE_COIN")
    private Long fkSaleCoin;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    @JsonIgnore
    @ManyToOne(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "walet_id")
    private Wallet wallets;
}
