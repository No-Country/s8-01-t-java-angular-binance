package com.s8.binance.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
	private  String mensaje;

	public Mensaje(String isEmptyNotDeleted, HttpStatus httpStatus) {
	}
}
