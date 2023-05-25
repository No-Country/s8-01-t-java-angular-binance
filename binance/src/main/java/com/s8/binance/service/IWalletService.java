package com.s8.binance.service;

import com.s8.binance.security.entity.User;

public interface IWalletService {

    public void  createWallet(User user);

    public void deleteWallet(Long id);
}
