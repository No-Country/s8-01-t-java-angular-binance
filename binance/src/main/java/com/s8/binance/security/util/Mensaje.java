package com.s8.binance.security.util;

import lombok.*;
import org.springframework.http.HttpStatus;

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
