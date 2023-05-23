package com.s8.binance.service;

import com.s8.binance.model.response.WalletResponseDto;
import com.s8.binance.security.entity.Usuario;

public interface IWalletService {

    public void  createWallet(Usuario usuario);

    public WalletResponseDto deleteWallet(Long id);
}
