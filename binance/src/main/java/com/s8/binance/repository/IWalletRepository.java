package com.s8.binance.repository;

import com.s8.binance.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWalletRepository extends JpaRepository<Wallet, Long> {
    
}