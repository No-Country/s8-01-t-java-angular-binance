package com.s8.binance.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
@ApiModel(description = "Payment method information")
public class PaymentMethodRequestDto implements Serializable {

    @NotBlank(message = "empty")
    @ApiModelProperty(value = "Payment type", example = "Credit card")
    private String paymentType;
}