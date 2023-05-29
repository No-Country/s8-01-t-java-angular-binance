package com.s8.binance.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponseDto {
    
    private String email;

    private String username;

    private String legalName;

    private String legalLastName;
}
