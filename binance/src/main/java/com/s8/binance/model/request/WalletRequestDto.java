package com.s8.binance.model.request;

import com.s8.binance.model.entity.Asset;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.s8.binance.model.entity.Wallet}
 */
@Value
public class WalletRequestDto implements Serializable {
    List<Asset> asset;
}