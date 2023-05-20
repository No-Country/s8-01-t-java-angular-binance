package com.s8.binance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Asset;

public interface IAssetRepository extends JpaRepository<Asset, Long> {

}