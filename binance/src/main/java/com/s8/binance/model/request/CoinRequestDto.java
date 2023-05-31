package com.s8.binance.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Currency information")
public class CoinRequestDto implements Serializable {

    @ApiModelProperty(example = "BTC")
    private String name;

    @ApiModelProperty(example = "Bitcoin")
    private String description;

    @ApiModelProperty(example = "27661.90")
    private BigDecimal usdValue;
}
