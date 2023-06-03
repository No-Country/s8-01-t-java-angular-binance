package com.s8.binance.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoinResponseDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Double usdValue;
}
