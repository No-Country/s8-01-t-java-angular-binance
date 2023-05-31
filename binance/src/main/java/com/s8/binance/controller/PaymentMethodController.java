package com.s8.binance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.PaymentMethodRequestDto;
import com.s8.binance.model.response.PaymentMethodResponseDto;
import com.s8.binance.service.IPaymentMethodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@Api(tags = "Payment methods", description = "Management of payment methods available in Binance.")
public class PaymentMethodController {

    private final IPaymentMethodService paymentMethodService;

    @GetMapping
    @ApiOperation("Get all payment methods.")
    public ResponseEntity<List<PaymentMethodResponseDto>> getAllPaymentMethods() {
        List<PaymentMethodResponseDto> responseEntity = paymentMethodService.getAllPaymentMethods();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a payment method by Id.")
    public ResponseEntity<PaymentMethodResponseDto> getPaymentMethodById(@PathVariable Long id) {
        PaymentMethodResponseDto responseEntity = paymentMethodService.getPaymentMethodById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PostMapping("/create")
    @ApiOperation("Create a new payment method.")
    public ResponseEntity<?> createPaymentMethod(
            @Valid @RequestBody PaymentMethodRequestDto paymentMethod) {
        paymentMethodService.createPaymentMethod(paymentMethod);
        return ResponseEntity.ok().body("Payment method successfully created");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an existing payment method by Id.")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok().body("Payment method successfully deleted");
    }
}
