package com.s8.binance.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.s8.binance.model.entity.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetRequestDto {

    @NotBlank(message = "empty")
    private String description;

    private BigDecimal total;

    private Long fkCoin;

    private Wallet wallet;
}