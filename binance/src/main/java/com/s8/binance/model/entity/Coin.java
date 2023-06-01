package com.s8.binance.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE coin SET status = true WHERE id=?")
@Where(clause = "status = false")
@Table(name = "COINS")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COIN_ID")
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "empty")
    private String name;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "empty")
    private String description;

    @Column(name = "USD_VALUE")
    @NotBlank(message = "empty")
    private BigDecimal usdValue;

    @Column(name = "STATUS")
    @Default
    private boolean status = Boolean.FALSE;
}
