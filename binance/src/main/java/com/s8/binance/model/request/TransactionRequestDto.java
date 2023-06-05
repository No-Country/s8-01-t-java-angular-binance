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

    @ApiModelProperty(example = "1")
    private Long purchaseCoinId;

    @ApiModelProperty(example = "27689.30")
    private Double purchaseAmount;

    @ApiModelProperty(example = "5")
    private Long saleCoinId;

    @ApiModelProperty(example = "474.50")
    private Double saleAmount;

    @ApiModelProperty(example = "1")
    private Long walletId;
}