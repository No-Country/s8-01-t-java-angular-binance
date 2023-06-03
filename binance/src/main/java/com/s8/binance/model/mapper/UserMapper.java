package com.s8.binance.model.mapper;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.s8.binance.model.request.UserRequestDto;
import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.model.response.UserListResponseDto;
import com.s8.binance.security.entity.User;

@Component
public class UserMapper {

    public UserListResponseDto fromEntityToDtoList(User user) {
        return UserListResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .legalName(user.getLegalName())
                .legalLastName(user.getLegalLastName())
                .walletId(user.getWallet().getId())
                .build();
    }

    public UserDetailsResponseDto fromEntityToDto(User user, HashMap<String, Double> balance) {
        return UserDetailsResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .legalName(user.getLegalName())
                .legalLastName(user.getLegalLastName())
                .birthdate(user.getBirthdate())
                .nationality(user.getNationality())
                .fullAddress(user.getFullAddress())
                .city(user.getCity())
                .zipCode(user.getZipCode())
                .country(user.getCountry())
                .balance(balance)
                .build();
    }

    public User updateUser(User user, UserRequestDto userRequestDto) {
        user.setEmail(userRequestDto.getEmail());
        user.setLegalName(userRequestDto.getLegalName());
        user.setLegalLastName(userRequestDto.getLegalLastName());
        user.setBirthdate(userRequestDto.getBirthdate());
        user.setNationality(userRequestDto.getNationality());
        user.setFullAddress(userRequestDto.getFullAddress());
        user.setCity(userRequestDto.getCity());
        user.setZipCode(userRequestDto.getZipCode());
        user.setCountry(userRequestDto.getCountry());
        return user;
    }
}
