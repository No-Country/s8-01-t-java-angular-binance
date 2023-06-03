package com.s8.binance.model.request;

import java.io.Serializable;

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
@ApiModel(description = "Transaction information")
public class TransactionRequestDto implements Serializable {

    @ApiModelProperty(example = "USDT")
    private String purchaseCoinName;

    @ApiModelProperty(example = "1.00")
    private Double purchaseAmount;

    @ApiModelProperty(example = "ARS")
    private String saleCoinName;

    @ApiModelProperty(example = "474.50")
    private Double saleAmount;

    @ApiModelProperty(example = "1")
    private Long walletId;
}