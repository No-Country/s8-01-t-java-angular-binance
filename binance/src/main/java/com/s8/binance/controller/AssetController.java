package com.s8.binance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.AssetRequestDto;
import com.s8.binance.model.response.AssetResponseDto;
import com.s8.binance.service.IAssetService;

import lombok.RequiredArgsConstructor;

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

    @PostMapping("/create")
    public ResponseEntity<AssetResponseDto> createAsset(@Valid @RequestBody AssetRequestDto asset){
        AssetResponseDto responseEntity = service.createAsset(asset);
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
