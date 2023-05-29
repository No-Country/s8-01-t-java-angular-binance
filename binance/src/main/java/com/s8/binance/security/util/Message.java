package com.s8.binance.security.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	private String message;

	public Message(String isEmptyNotDeleted, HttpStatus httpStatus) {}
}
