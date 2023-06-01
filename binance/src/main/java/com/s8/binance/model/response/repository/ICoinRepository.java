package com.s8.binance.model.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Coin;

public interface ICoinRepository extends JpaRepository<Coin, Long> {

}
