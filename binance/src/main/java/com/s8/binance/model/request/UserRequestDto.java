package com.s8.binance.model.request;

import io.swagger.annotations.ApiModel;
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

    private String email;

    private String legalName;

    private String legalLastName;

    private String birthdate;

    private String nationality;

    private String fullAddress;

    private String city;

    private String zipCode;

    private String country;
}
