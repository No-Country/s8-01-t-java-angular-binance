package com.s8.binance.model.request;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.Wallet;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.s8.binance.model.entity.Asset}
 */
@Value
public class AssetRequestDto implements Serializable {
    @NotBlank(message = "Empty")
    String description;
    BigDecimal total;
    Coin coin;
    Wallet wallet;
}