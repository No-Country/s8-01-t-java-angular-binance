package com.s8.binance.model.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Wallet;

public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    
}