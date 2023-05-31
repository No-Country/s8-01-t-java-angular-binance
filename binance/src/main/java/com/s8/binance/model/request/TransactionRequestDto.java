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

    @ApiModelProperty(example = "PURCHASE")
    private TransactionType transactionType;

    @ApiModelProperty(example = "1")
    private Long purchaseCoinId;

    @ApiModelProperty(example = "200.00")
    private BigDecimal purchaseAmount;

    @ApiModelProperty(example = "1")
    private Long saleCoinId;

    @ApiModelProperty(example = "200.00")
    private BigDecimal saleAmount;

    @ApiModelProperty(example = "1")
    private Long walletId;
}