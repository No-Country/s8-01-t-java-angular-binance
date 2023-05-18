package com.s8.binance.service;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.request.CoinRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.model.response.CoinResponseDto;

import java.util.List;

public interface IAssetService {

    public List<AssetResponseDto> getAll();
    public AssetResponseDto getAssetById(Long id);
    public Asset getAssetByFilters(); //filtra por todo
    public Asset createAsset(AssetRequestDto assetRequestDto);
    public AssetResponseDto updateAsset(Long id, AssetRequestDto assetRequestDto);
    public AssetResponseDto deleteAsset(Long id);
}
