package com.s8.binance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.s8.binance.security.entity.Usuario;
import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.mapper.WalletMapper;
import com.s8.binance.model.request.WalletRequestDto;
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
    public List<WalletResponseDto> getAll() {
        List<Wallet> wallets = repository.findAll();
        List<WalletResponseDto> WalletResponseDtoList = new ArrayList<>();
        wallets.forEach(wallet -> {
            WalletResponseDto response = mapper.fromEntityToDto(wallet);
            WalletResponseDtoList.add(response);
        });
        return WalletResponseDtoList;
    }

    @Override
    public WalletResponseDto getWalletById(Long id) {
        Wallet wallet = repository.findById(id).orElseThrow();
        WalletResponseDto response = mapper.fromEntityToDto(wallet);
        return response;
    }

    @Override
    public List<WalletResponseDto> getWalletsByFilters() {
        throw new UnsupportedOperationException("Unimplemented method 'getWalletsByFilters'");
    }

    @Override
    public WalletResponseDto createWallet(Usuario usuario) {
        Wallet wallet = new Wallet();
        wallet.setUsuario(usuario);
        repository.save(wallet);
        WalletResponseDto response = mapper.fromEntityToDto(wallet);
        return response;
    }

    // @Override
    // @Transactional
    // public WalletResponseDto updateWallet(Long id, WalletRequestDto walletRequestDto) {
    //     Wallet wallet = repository.findById(id).orElseThrow();
    //     Wallet updatedWallet = mapper.updateWallet(wallet, walletRequestDto);
    //     repository.save(updatedWallet);
    //     WalletResponseDto response = mapper.fromEntityToDto(updatedWallet);
    //     return response;
    // }

    @Override
    public WalletResponseDto deleteWallet(Long id) {
        repository.deleteById(id);
        return null;
    }
}
