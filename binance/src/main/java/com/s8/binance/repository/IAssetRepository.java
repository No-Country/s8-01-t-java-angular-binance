package com.s8.binance.repository;

import com.s8.binance.model.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssetRepository extends JpaRepository<Asset, Long> {
}