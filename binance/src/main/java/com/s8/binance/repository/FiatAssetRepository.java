package com.s8.binance.repository;

import com.s8.binance.model.entity.FiatAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiatAssetRepository extends JpaRepository<FiatAsset, Long> {
}