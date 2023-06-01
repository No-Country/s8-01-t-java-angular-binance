package com.s8.binance.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findAll(Specification<Transaction> spec);

    public List<Transaction> findByWalletId(Long walletId);
}