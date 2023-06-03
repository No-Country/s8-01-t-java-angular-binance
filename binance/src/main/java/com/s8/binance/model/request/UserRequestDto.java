package com.s8.binance.model.request;

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
@ApiModel(description = "User information")
public class UserRequestDto {

    @ApiModelProperty(example = "lionelmessi@gmail.com")
    private String email;

    @ApiModelProperty(example = "Lionel")
    private String legalName;

    @ApiModelProperty(example = "Messi")
    private String legalLastName;

    @ApiModelProperty(example = "1986-06-24")
    private String birthdate;

    @ApiModelProperty(example = "Marte")
    private String nationality;

    @ApiModelProperty(example = "Castelldefels 123")
    private String fullAddress;

    @ApiModelProperty(example = "Barcelona")
    private String city;

    @ApiModelProperty(example = "08001")
    private String zipCode;

    @ApiModelProperty(example = "España")
    private String country;
}
