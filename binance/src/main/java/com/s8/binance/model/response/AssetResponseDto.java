package com.s8.binance.model.response;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.Wallet;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.s8.binance.model.entity.Asset}
 */
@Value
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AssetResponseDto implements Serializable {
    private Long id;

    @NotBlank(message = "Empty")
    private String description;

    private BigDecimal total;

    private Long fkCoin;

    private Wallet wallet;
}