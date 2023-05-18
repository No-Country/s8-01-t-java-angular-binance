package com.s8.binance.repository;

import com.s8.binance.model.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoinRepository extends JpaRepository<Coin, Long> {
}