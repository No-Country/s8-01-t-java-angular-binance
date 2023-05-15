package com.s8.binance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Coin;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICoinRepository extends JpaRepository<Coin, Long> {


}
