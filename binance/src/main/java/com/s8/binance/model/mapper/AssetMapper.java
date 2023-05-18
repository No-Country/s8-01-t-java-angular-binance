package com.s8.binance.model.mapper;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public AssetResponseDto fromEntityToDto(Asset asset) {
        return AssetResponseDto.builder()
                .id(asset.getId())
                .description(asset.getDescription())
                .total(asset.getTotal())
                .coin(asset.getCoin())
                .wallet(asset.getWallet())
                .build();
    }

    public Asset fromDtoToEntity(AssetRequestDto assetRequestDto){
        return Asset.builder()
                .description(assetRequestDto.getDescription())
                .total(assetRequestDto.getTotal())
                .coin(assetRequestDto.getCoin())
                .wallet(assetRequestDto.getWallet())
                .build();
    }

    public Asset updateAsset(Asset asset, AssetRequestDto assetRequestDto){
        asset.setTotal(assetRequestDto.getTotal());
        asset.setDescription(asset.getDescription());
        return asset;
    }
}
