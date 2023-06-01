package com.s8.binance.model.response;

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
public class UserListResponseDto {
    
    private Long id;

    private String email;

    private String username;

    private String legalName;

    private String legalLastName;

    private Long walletId;
}
