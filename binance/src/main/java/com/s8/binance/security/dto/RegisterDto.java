package com.s8.binance.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "User registration information")
public class RegisterDto {

	@Email
	@ApiModelProperty(example = "antonelaroccuzzo@gmail.com")
	private String email;

	@NotBlank
	@ApiModelProperty(example = "password1234")
	private String password;

	@NotBlank
	@ApiModelProperty(example = "antonelaroccuzzo")
	private String username;

	@NotBlank
	@ApiModelProperty(example = "Antonela")
	private String legalName;

	@NotBlank
	@ApiModelProperty(example = "Roccuzzo")
	private String legalLastName;

	@NotBlank
	@ApiModelProperty(example = "1988-02-26")
	private String birthdate;

	@NotBlank
	@ApiModelProperty(example = "Argentina")
	private String nationality;

	@NotBlank
	@ApiModelProperty(example = "Avenue Montaigne 123")
	private String fullAddress;

	@NotBlank
	@ApiModelProperty(example = "Paris")
	private String city;

	@NotBlank
	@ApiModelProperty(example = "70123")
	private String zipCode;

	@NotBlank
	@ApiModelProperty(example = "France")
	private String country;
}
