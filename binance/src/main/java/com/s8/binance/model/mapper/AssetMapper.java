package com.s8.binance.model.mapper;

import org.springframework.stereotype.Component;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;

@Component
public class AssetMapper {

    public AssetResponseDto fromEntityToDto(Asset asset) {
        return AssetResponseDto.builder()
                .id(asset.getId())
                .description(asset.getDescription())
                .total(asset.getTotal())
                .fkCoin(asset.getFkCoin())
                .build(); 
    }

    public Asset fromDtoToEntity(AssetRequestDto assetRequestDto){
        return Asset.builder()
                .description(assetRequestDto.getDescription())
                .total(assetRequestDto.getTotal())
                .fkCoin(assetRequestDto.getFkCoin())
                // .wallet(assetRequestDto.getWallet())
                .build();
    }

    public Asset updateAsset(Asset asset, AssetRequestDto assetRequestDto){
        asset.setTotal(assetRequestDto.getTotal());
        asset.setDescription(asset.getDescription());
        return asset;
    }
}
