package com.s8.binance.model.entity;

import java.math.BigDecimal;

import javax.persistence.*;

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
@Table(name = "CRYPTO_ASSET")
public class CryptoAsset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Empty")
    private String description;

    @Column(name = "TOTAL")
    private BigDecimal total;

    //@Column(name = "COIN_ID")
    //private Coin coinId;

    @ManyToOne
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;
}
