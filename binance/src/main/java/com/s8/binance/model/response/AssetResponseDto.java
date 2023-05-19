package com.s8.binance.model.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * DTO for {@link com.s8.binance.model.entity.Asset}
 */
@Value
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AssetResponseDto implements Serializable {
    Long id;
    @NotBlank(message = "Empty")
    String description;
    BigDecimal total;
    Coin coin;
    Wallet wallet;
}