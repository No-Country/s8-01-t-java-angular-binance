package com.s8.binance.repository;

import com.s8.binance.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
}
