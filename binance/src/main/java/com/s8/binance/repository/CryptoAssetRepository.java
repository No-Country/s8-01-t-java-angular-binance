package com.s8.binance.repository;

import com.s8.binance.model.entity.CryptoAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoAssetRepository extends JpaRepository<CryptoAsset, Long> {
}