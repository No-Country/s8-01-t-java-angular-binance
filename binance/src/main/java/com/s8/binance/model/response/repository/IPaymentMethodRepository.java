package com.s8.binance.model.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.model.entity.PaymentMethod;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    public PaymentMethod findByPaymentType(String name);
}