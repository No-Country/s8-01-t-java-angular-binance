package com.s8.binance.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponseDto {

    private String email;

    private String username;

    private String legalName;

    private String legalLastName;

    private String birthdate;

    private String nationality;

    private String fullAddress;

    private String city;

    private String zipCode;

    private String country;

    private List<TransactionResponseDto> transactions;
}
