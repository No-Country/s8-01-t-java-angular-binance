package com.s8.binance.service.impl;

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
            LocalDate transactionDate, Long purchaseCoinId, Double purchaseAmount, Long saleCoinId,
            Double saleAmount, Long walletId) {

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
    public HashMap<String, Double> getWalletBalance(Long walletId) {
        HashMap<String, Double> walletBalance = new HashMap<>();
        List<Transaction> transactions = transactionRepository.findByWalletId(walletId);

        for (Transaction transaction : transactions) {
            String purchaseCoinName = transaction.getPurchaseCoin().getName();
            Double purchaseAmount = transaction.getPurchaseAmount();

            if (walletBalance.containsKey(purchaseCoinName)) {
                Double total = walletBalance.get(purchaseCoinName);
                walletBalance.put(purchaseCoinName, total + purchaseAmount);
            } else {
                walletBalance.put(purchaseCoinName, purchaseAmount);
            }

            if (transaction.getSaleCoin() != null) {
                String saleCoinName = transaction.getSaleCoin().getName();
                Double saleAmount = transaction.getSaleAmount();
                Double total = walletBalance.get(saleCoinName);
                walletBalance.put(saleCoinName, total - saleAmount);
            }
        }

        return walletBalance;
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

        this.checkBalance(saleCoin, transactionRequestDto.getSaleAmount(), wallet);
        Transaction transaction = transactionMapper.fromTransactionDtoToEntity(transactionRequestDto, paymentMethod,
                purchaseCoin, saleCoin, wallet);
        transactionRepository.save(transaction);
        return transactionMapper.fromEntityToTransactionDto(transaction);
    }

    private void checkBalance(Coin saleCoin, Double saleAmount, Wallet wallet) {
        HashMap<String, Double> walletBalance = this.getWalletBalance(wallet.getId());

        if (!walletBalance.containsKey(saleCoin.getName())) {
            throw new RuntimeException("Coin does not exist in the wallet");
        }

        if (walletBalance.get(saleCoin.getName()) < saleAmount) {
            throw new RuntimeException("Insufficient funds to complete the transaction");
        }
    }
}
