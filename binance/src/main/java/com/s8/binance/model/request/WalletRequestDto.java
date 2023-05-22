package com.s8.binance.model.request;

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
public class WalletRequestDto {

    private Long id;

    private List<Transaction> transaction = new ArrayList<>();
}