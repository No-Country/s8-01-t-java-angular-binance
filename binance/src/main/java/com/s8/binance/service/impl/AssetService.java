package com.s8.binance.service.impl;

import java.util.List;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.service.IAssetService;

public class AssetService implements IAssetService {

    @Override
    public List<AssetResponseDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAssetById'");
    }

    @Override
    public Asset getAssetByFilters() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAssetByFilters'");
    }

    @Override
    public Asset createAsset(AssetRequestDto assetRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAsset'");
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetRequestDto assetRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAsset'");
    }

    @Override
    public AssetResponseDto deleteAsset(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAsset'");
    }
}
