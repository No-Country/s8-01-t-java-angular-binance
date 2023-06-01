package com.s8.binance.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MessageDto {

    private HttpStatus status;
    private String message;
}
