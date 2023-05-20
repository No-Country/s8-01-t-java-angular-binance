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
                .paymentDate(asset.getPaymentDate())
                .paymentType(asset.getPaymentType())
                .build();
    }

    public Asset fromDtoToEntity(AssetRequestDto assetRequestDto) {
        return Asset.builder()
                .paymentDate(assetRequestDto.getPaymentDate())
                .paymentType(assetRequestDto.getPaymentType())
                .build();
    }

    public Asset updateAsset(Asset asset, AssetRequestDto assetRequestDto) {
        asset.setPaymentDate(assetRequestDto.getPaymentDate());
        asset.setPaymentDate(assetRequestDto.getPaymentDate());
        return asset;
    }
}
