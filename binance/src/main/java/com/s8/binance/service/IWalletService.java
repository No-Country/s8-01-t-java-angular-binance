package com.s8.binance.service;

import java.util.List;

import com.s8.binance.model.request.WalletRequestDto;
import com.s8.binance.model.response.WalletResponseDto;
import com.s8.binance.security.entity.Usuario;

public interface IWalletService {

    public List<WalletResponseDto> getAll();

    public WalletResponseDto getWalletById(Long id);

    public List<WalletResponseDto> getWalletsByFilters();

    public WalletResponseDto createWallet(Usuario usuario);

    // public WalletResponseDto updateWallet(Long id, WalletRequestDto WalletRequestDto);

    public WalletResponseDto deleteWallet(Long id);
}
