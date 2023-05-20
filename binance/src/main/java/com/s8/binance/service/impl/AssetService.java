package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.mapper.AssetMapper;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.repository.IAssetRepository;
import com.s8.binance.service.IAssetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetService implements IAssetService {

    private final IAssetRepository repository;

    private final AssetMapper mapper;

    @Override
    public List<AssetResponseDto> getAll() {
        List<Asset> assets = repository.findAll();
        List<AssetResponseDto> assetResponseDtoList = new ArrayList<>();
        assets.forEach(asset -> {
            AssetResponseDto response = mapper.fromEntityToDto(asset);
            assetResponseDtoList.add(response);
        });
        return assetResponseDtoList;
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {
        Asset asset = repository.findById(id).orElseThrow();
        AssetResponseDto response = mapper.fromEntityToDto(asset);
        return response;
    }

    @Override
    public List<AssetResponseDto> getAssetsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getAssetsByFilters'");
    }

    @Override
    public AssetResponseDto createAsset(AssetRequestDto assetRequestDto) {
        Asset asset = mapper.fromDtoToEntity(assetRequestDto);
        repository.save(asset);
        AssetResponseDto response = mapper.fromEntityToDto(asset);
        return response;
    }

    @Override
    @Transactional
    public AssetResponseDto updateAsset(Long id, AssetRequestDto assetRequestDto) {
        Asset asset = repository.findById(id).orElseThrow();
        Asset updatedAsset = mapper.updateAsset(asset, assetRequestDto);
        repository.save(updatedAsset);
        AssetResponseDto response = mapper.fromEntityToDto(updatedAsset);
        return response;
    }

    @Override
    public AssetResponseDto deleteAsset(Long id) {
        repository.deleteById(id);
        return null;
    }
}
