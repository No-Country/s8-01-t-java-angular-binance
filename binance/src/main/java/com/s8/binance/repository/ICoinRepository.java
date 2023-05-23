package com.s8.binance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Coin;

public interface ICoinRepository extends JpaRepository<Coin, Long> {

    Optional<Coin> findByName(String name);
}
