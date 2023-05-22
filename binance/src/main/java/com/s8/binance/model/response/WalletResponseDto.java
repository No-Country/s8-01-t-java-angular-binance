package com.s8.binance.model.response;

import java.util.ArrayList;
import java.util.List;

import com.s8.binance.model.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseDto {

    private Long id;

    private List<Transaction> transactions = new ArrayList<>();
}