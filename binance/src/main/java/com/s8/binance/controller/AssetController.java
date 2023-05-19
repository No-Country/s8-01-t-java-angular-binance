package com.s8.binance.controller;

import com.s8.binance.model.entity.Asset;
import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.service.IAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/assets")
@RequiredArgsConstructor
public class AssetController {

    private final IAssetService service;

    @GetMapping
    public ResponseEntity<List<AssetResponseDto>> getAllAssets(){
        List<AssetResponseDto> assets = service.getAll();
        return ResponseEntity.ok().body(assets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponseDto> getAssetById(@PathVariable Long id){
        AssetResponseDto responseEntity =  service.getAssetById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create") public ResponseEntity<?> createAsset(@Valid @RequestBody AssetRequestDto asset){
        Asset responseEntity = service.createAsset(asset);
        return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AssetResponseDto> updateAsset(@Valid @PathVariable Long id, @RequestBody AssetRequestDto assetRequestDto){
        AssetResponseDto responseEntity = service.updateAsset(id, assetRequestDto);
        return  ResponseEntity.ok().body(responseEntity);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable Long id){
        AssetResponseDto responseEntity = service.deleteAsset(id);
        return  ResponseEntity.ok().body(responseEntity);
    }
}