package com.s8.binance.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.s8.binance.util.enums.TransactionType;

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

    @ApiModelProperty(value = "Transaction type", example = "PURCHASE")
    private TransactionType transactionType;

    @ApiModelProperty(value = "Purchase coin Id", example = "1")
    private Long purchaseCoinId;

    @ApiModelProperty(value = "Purchase amount", example = "200.00")
    private BigDecimal purchaseAmount;

    @ApiModelProperty(value = "Sale coin Id", example = "1")
    private Long saleCoinId;

    @ApiModelProperty(value = "Sale amount", example = "200.00")
    private BigDecimal saleAmount;

    @ApiModelProperty(value = "Wallet Id", example = "1")
    private Long walletId;
}