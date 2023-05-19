package com.s8.binance.service.impl;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.service.IAssetService;

import java.util.List;

public class AssetService implements IAssetService {
    @Override
    public List<AssetResponseDto> getAll() {
        return null;
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {
        return null;
    }

    @Override
    public Asset getAssetByFilters() {
        return null;
    }

    @Override
    public Asset createAsset(AssetRequestDto assetRequestDto) {
        return null;
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetRequestDto assetRequestDto) {
        return null;
    }

    @Override
    public AssetResponseDto deleteAsset(Long id) {
        return null;
    }
}
