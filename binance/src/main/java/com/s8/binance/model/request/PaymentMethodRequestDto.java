package com.s8.binance.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
@ApiModel(description = "Payment method information")
public class PaymentMethodRequestDto implements Serializable {

    @NotBlank(message = "empty")
    @ApiModelProperty(example = "P2P Trading")
    private String paymentType;
}