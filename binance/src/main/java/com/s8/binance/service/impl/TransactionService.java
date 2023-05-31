package com.s8.binance.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.s8.binance.model.entity.Coin;
import com.s8.binance.model.entity.PaymentMethod;
import com.s8.binance.model.entity.Transaction;
import com.s8.binance.model.entity.Wallet;
import com.s8.binance.model.mapper.TransactionMapper;
import com.s8.binance.model.request.DepositRequestDto;
import com.s8.binance.model.request.TransactionRequestDto;
import com.s8.binance.model.response.DepositResponseDto;
import com.s8.binance.model.response.TransactionResponseDto;
import com.s8.binance.repository.ICoinRepository;
import com.s8.binance.repository.IPaymentMethodRepository;
import com.s8.binance.repository.ITransactionRepository;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.service.ITransactionService;
import com.s8.binance.specification.TransactionSpecification;
import com.s8.binance.util.enums.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionMapper transactionMapper;

    private final ITransactionRepository transactionRepository;

    private final IPaymentMethodRepository paymentMethodRepository;

    private final ICoinRepository coinRepository;

    private final IWalletRepository walletRepository;

    @Override
    public List<TransactionResponseDto> getTransactionsByFilters(Long paymentMethodId,
            TransactionType transactionType,
            LocalDate transactionDate, Long purchaseCoinId, BigDecimal purchaseAmount, Long saleCoinId,
            BigDecimal saleAmount, Long walletId) {

        Specification<Transaction> spec = Specification.where(null);

        if (paymentMethodId != null) {
            spec = spec.and(TransactionSpecification.hasPaymentMethod(paymentMethodId));
        }

        if (transactionType != null) {
            spec = spec.and(TransactionSpecification.hasTransactionType(transactionType));
        }

        if (transactionDate != null) {
            spec = spec.and(TransactionSpecification.hasTransactionDate(transactionDate));
        }

        if (purchaseCoinId != null) {
            spec = spec.and(TransactionSpecification.hasPurchaseCoin(purchaseCoinId));
        }

        if (purchaseAmount != null) {
            spec = spec.and(TransactionSpecification.hasPurchaseAmount(purchaseAmount));
        }

        if (saleCoinId != null) {
            spec = spec.and(TransactionSpecification.hasSaleCoin(saleCoinId));
        }

        if (saleAmount != null) {
            spec = spec.and(TransactionSpecification.hasSaleAmount(saleAmount));
        }

        if (walletId != null) {
            spec = spec.and(TransactionSpecification.hasWalletId(walletId));
        }

        List<Transaction> transactionsFiltered = transactionRepository.findAll(spec);
        List<TransactionResponseDto> TransactionResponseDtoList = new ArrayList<>();
        for (Transaction transaction : transactionsFiltered) {
            TransactionResponseDto transactionResponseDto = transactionMapper.fromEntityToTransactionDto(transaction);
            TransactionResponseDtoList.add(transactionResponseDto);
        }

        return TransactionResponseDtoList;
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        TransactionResponseDto response = transactionMapper.fromEntityToTransactionDto(transaction);
        return response;
    }

    @Override
    public HashMap<String, BigDecimal> getWalletBalance(Long walletId) {
        HashMap<String, BigDecimal> map = new HashMap<>();
        List<Transaction> transactions = transactionRepository.findByWalletId(walletId);
        for (Transaction transaction : transactions) {
            String purchaseCoinName = transaction.getPurchaseCoin().getName();
            BigDecimal purchaseAmount = transaction.getPurchaseAmount();
            if (map.containsKey(purchaseCoinName)) {
                BigDecimal total = map.get(purchaseCoinName);
                map.put(purchaseCoinName, total.add(purchaseAmount));
            } else {
                map.put(purchaseCoinName, purchaseAmount);
            }

            if (transaction.getSaleCoin() != null) {
                String saleCoinName = transaction.getSaleCoin().getName();
                BigDecimal saleAmount = transaction.getSaleAmount();
                if (map.containsKey(saleCoinName)) {
                    BigDecimal total = map.get(saleCoinName);
                    map.put(saleCoinName, total.subtract(saleAmount));
                } else {
                    map.put(saleCoinName, saleAmount.negate());
                }
            }
        }

        return map;
    }

    @Override
    public DepositResponseDto createDeposit(DepositRequestDto depositRequestDto) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByPaymentType("Bank Deposit");
        Coin depositCoin = coinRepository.findById(depositRequestDto.getDepositCoinId()).orElseThrow();
        Wallet wallet = walletRepository.findById(depositRequestDto.getWalletId()).orElseThrow();
        Transaction deposit = transactionMapper.fromDepositDtoToEntity(depositRequestDto, paymentMethod,
                depositCoin, wallet);
        transactionRepository.save(deposit);
        return transactionMapper.fromEntityToDepositDto(deposit);
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByPaymentType("Cash Balance");
        Coin purchaseCoin = coinRepository.findById(transactionRequestDto.getPurchaseCoinId()).orElseThrow();
        Coin saleCoin = coinRepository.findById(transactionRequestDto.getSaleCoinId()).orElseThrow();
        Wallet wallet = walletRepository.findById(transactionRequestDto.getWalletId()).orElseThrow();

        // checkear si hay saldo suficiente

        // calcular tipo de cambio

        Transaction transaction = transactionMapper.fromTransactionDtoToEntity(transactionRequestDto, paymentMethod,
                purchaseCoin, saleCoin, wallet);

        transactionRepository.save(transaction);
        return transactionMapper.fromEntityToTransactionDto(transaction);
    }
}
