package com.s8.binance.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.security.entity.User;
import com.s8.binance.service.IWalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService implements IWalletService {
    private final IWalletRepository repository;
    @Override
    public void createWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        repository.save(wallet);
    }
    
    @Override
    public void deleteWallet(Long id) {
        repository.deleteById(id);
    }
}
