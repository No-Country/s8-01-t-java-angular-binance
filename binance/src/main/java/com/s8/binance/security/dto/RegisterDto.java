package com.s8.binance.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User registration information")
public class RegisterDto {

	@Email
	@ApiModelProperty(value = "User email", example = "antonelaroccuzzo@gmail.com")
	private String email;

	@NotBlank
	@ApiModelProperty(value = "New password", example = "password1234")
	private String password;

	@NotBlank
	@ApiModelProperty(value = "New username", example = "antonelaroccuzzo")
	private String username;

	@NotBlank
	@ApiModelProperty(value = "First name", example = "Antonela")
	private String legalName;

	@NotBlank
	@ApiModelProperty(value = "Last name", example = "Roccuzzo")
	private String legalLastName;

	@NotBlank
	@ApiModelProperty(value = "Birth date", example = "1988-02-26")
	private String birthdate;

	@NotBlank
	@ApiModelProperty(value = "Nationality", example = "Argentina")
	private String nationality;

	@NotBlank
	@ApiModelProperty(value = "Address", example = "Avenue Montaigne 123")
	private String fullAddress;

	@NotBlank
	@ApiModelProperty(value = "City", example = "Paris")
	private String city;

	@NotBlank
	@ApiModelProperty(value = "Zip Code", example = "70123")
	private String zipCode;

	@NotBlank
	@ApiModelProperty(value = "Country", example = "France")
	private String country;

	@NotNull
	@ApiModelProperty(example = "true")
	private boolean agree;
	private Set<String> roles = new HashSet<>();
}
