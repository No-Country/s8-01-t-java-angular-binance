package com.s8.binance.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;

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
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK_PURCHASE_COIN", referencedColumnName = "ID_COIN", insertable = false, updatable = false)
//    private Coin purchaseCoin;
    @Column(name = "PURCHASE_AMOUNT")
    private BigDecimal purchaseAmount;
    @Column(name = "FK_SALE_COIN")
    private Long fkSaleCoin;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    @JsonIgnore
    @ManyToOne(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "walet_id")
    Wallet wallets;

}


