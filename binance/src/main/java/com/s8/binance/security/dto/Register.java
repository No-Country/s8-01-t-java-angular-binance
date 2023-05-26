package com.s8.binance.security.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Register {

	@Email
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String username;

	@NotBlank
	private String legalName;

	@NotBlank
	private String legalLastName;

	@NotBlank
	private String birthdate;

	@NotBlank
	private String fullAddress;

	@NotBlank
	private String postalCode;

	@NotBlank
	private String nationality;

	@NotBlank
	private String city;

	@NotBlank
	private String country;

	private Set<String> roles = new HashSet<>();
}