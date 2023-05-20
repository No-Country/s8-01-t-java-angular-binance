package com.s8.binance.model.response;

import java.io.Serializable;
import java.util.List;

import com.s8.binance.model.entity.Asset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseDto implements Serializable {

    private Long id;

    private List<Asset> assets;
}