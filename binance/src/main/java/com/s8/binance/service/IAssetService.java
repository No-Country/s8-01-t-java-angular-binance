package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;

public interface IAssetService {

    public List<AssetResponseDto> getAll();

    public AssetResponseDto getAssetById(Long id);

    public List<AssetResponseDto> getAssetsByFilters();

    public AssetResponseDto createAsset(AssetRequestDto assetRequestDto);

    public AssetResponseDto updateAsset(Long id, AssetRequestDto assetRequestDto);

    public AssetResponseDto deleteAsset(Long id);
}
