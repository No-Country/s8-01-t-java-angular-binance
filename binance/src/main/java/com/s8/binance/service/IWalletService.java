package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.entity.Wallet;

public interface IWalletService {

    public List<Wallet> getAllWallets();

    public Wallet getWalletById(Long id);
    
    public void deleteWallet(Long id);
}
