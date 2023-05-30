package com.s8.binance.model.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User information")
public class UserRequestDto {

    private String legalName;

    private String legalLastName;

    private String birthdate;

    private String nationality;

    private String fullAddress;

    private String city;

    private String zipCode;

    private String country;
}
