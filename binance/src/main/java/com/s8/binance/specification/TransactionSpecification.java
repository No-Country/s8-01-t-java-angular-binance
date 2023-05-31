package com.s8.binance.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.s8.binance.model.entity.Transaction;
import com.s8.binance.util.enums.TransactionType;

public class TransactionSpecification {

    public static Specification<Transaction> hasPaymentMethod(Long paymentMethodId) {
        return (root, query, cb) -> cb.equal(root.get("paymentMethod").get("id"), paymentMethodId);
    }

    public static Specification<Transaction> hasTransactionType(TransactionType transactionType) {
        return (root, query, cb) -> cb.equal(root.get("transactionType"), transactionType);
    }

    public static Specification<Transaction> hasTransactionDate(LocalDate transactionDate) {
        return (root, query, cb) -> cb.equal(root.get("transactionDate"), transactionDate);
    }

    public static Specification<Transaction> hasPurchaseCoin(Long purchaseCoinId) {
        return (root, query, cb) -> cb.equal(root.get("purchaseCoin").get("id"), purchaseCoinId);
    }

    public static Specification<Transaction> hasPurchaseAmount(BigDecimal purchaseAmount) {
        return (root, query, cb) -> cb.equal(root.get("purchaseAmount"), purchaseAmount);
    }

    public static Specification<Transaction> hasSaleCoin(Long saleCoinId) {
        return (root, query, cb) -> cb.equal(root.get("saleCoin").get("id"), saleCoinId);
    }

    public static Specification<Transaction> hasSaleAmount(BigDecimal saleAmount) {
        return (root, query, cb) -> cb.equal(root.get("saleAmount"), saleAmount);
    }

    public static Specification<Transaction> hasWalletId(Long walletId) {
        return (root, query, cb) -> cb.equal(root.get("wallet").get("id"), walletId);
    }
}
