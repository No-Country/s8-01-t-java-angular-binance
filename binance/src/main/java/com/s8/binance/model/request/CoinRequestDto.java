package com.s8.binance.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Coin information")
public class CoinRequestDto implements Serializable {

    @ApiModelProperty(value = "Coin name", example = "BTC")
    private String name;

    @ApiModelProperty(value = "Coin description", example = "Bitcoin")
    private String description;
}
