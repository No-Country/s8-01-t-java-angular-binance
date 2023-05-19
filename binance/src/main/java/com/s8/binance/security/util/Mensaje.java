package com.s8.binance.security.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensaje {
	private  String mensaje;

	public Mensaje(String isEmptyNotDeleted, HttpStatus httpStatus) {
	}
}
