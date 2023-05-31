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
@ApiModel(description = "Deposit information")
public class DepositRequestDto implements Serializable {

    @ApiModelProperty(value = "Payment method Id", example = "1")
    private Long paymentMethodId;

    @ApiModelProperty(value = "Deposit coin Id", example = "5")
    private Long depositCoinId;

    @ApiModelProperty(value = "Purchase amount", example = "5000.00")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "Wallet Id", example = "1")
    private Long walletId;
}