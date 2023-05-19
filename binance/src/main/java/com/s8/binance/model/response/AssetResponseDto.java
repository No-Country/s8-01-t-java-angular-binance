package com.s8.binance.model.response;

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
public class AssetResponseDto {
    
    private Long id;
    
    @NotBlank(message = "Empty")
    private String description;
    
    private BigDecimal total;
    
    private Long fkCoin;

    private Wallet wallet;
}