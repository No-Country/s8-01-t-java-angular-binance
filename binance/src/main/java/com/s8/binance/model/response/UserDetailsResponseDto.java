package com.s8.binance.model.response;

import java.util.HashMap;

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
public class UserDetailsResponseDto {

    private Long id;

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

    private HashMap<String, Double> balance;
}
