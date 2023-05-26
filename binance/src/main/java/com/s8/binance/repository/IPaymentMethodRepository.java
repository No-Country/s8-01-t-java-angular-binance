package com.s8.binance.repository;

import com.s8.binance.model.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}