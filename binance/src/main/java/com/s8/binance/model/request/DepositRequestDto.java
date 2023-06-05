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
@ApiModel(description = "Deposit information")
public class DepositRequestDto implements Serializable {

    @ApiModelProperty(example = "5")
    private Long depositCoinId;

    @ApiModelProperty(example = "5000.00")
    private Double depositAmount;

    @ApiModelProperty(example = "1")
    private Long walletId;
}