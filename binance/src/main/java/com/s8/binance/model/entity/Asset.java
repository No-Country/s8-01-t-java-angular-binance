package com.s8.binance.model.entity;

import java.math.BigDecimal;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ASSETS")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASSET")
    private Long id;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Empty")
    private String description;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "FK_COIN")
    private Long fkCoin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COIN", referencedColumnName = "ID_COIN", insertable = false, updatable = false)
    private Coin coin;

    @ManyToOne
    @JoinColumn(name = "ID_WALLET")
    private Wallet wallet;
}
