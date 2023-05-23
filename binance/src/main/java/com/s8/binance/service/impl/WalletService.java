package com.s8.binance.service.impl;

import javax.transaction.Transactional;

import com.s8.binance.security.entity.Usuario;
import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.mapper.WalletMapper;
import com.s8.binance.model.response.WalletResponseDto;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.service.IWalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class WalletService implements IWalletService {
    private final IWalletRepository repository;
    private final WalletMapper mapper;
    @Override
    public void createWallet(Usuario usuario) {
        Wallet wallet = new Wallet();
        wallet.setUsuario(usuario);
        repository.save(wallet);
      //  WalletResponseDto response = mapper.fromEntityToDto(wallet);

    }
    @Override
    public WalletResponseDto deleteWallet(Long id) {
        repository.deleteById(id);
        return null;
    }
}
