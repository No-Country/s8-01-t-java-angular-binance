package com.s8.binance.repository;

import com.s8.binance.model.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByName(String name);

    boolean existsByName(String name);
}