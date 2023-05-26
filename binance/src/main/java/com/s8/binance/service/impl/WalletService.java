package com.s8.binance.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.service.IWalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService implements IWalletService {

    private final IWalletRepository walletRepository;

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow();
    }

    // @Override
    // public void createWallet(User user) {
    //     Wallet wallet = new Wallet();
    //     wallet.setUser(user);
    //     walletRepository.save(wallet);
    // }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
