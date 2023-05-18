package com.s8.binance.model.response;

import com.s8.binance.model.entity.Asset;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.s8.binance.model.entity.Wallet}
 */
@Value
public class WalletResponseDto implements Serializable {
    Long id;
    List<Asset> asset;
}