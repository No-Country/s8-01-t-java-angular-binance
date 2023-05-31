package com.s8.binance.security.dto;

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
@ApiModel(description = "User login information")
public class LoginDto {

	@NotBlank
	@ApiModelProperty(value = "Username", example = "antonelaroccuzzo")
	private String username;

	@NotBlank
	@ApiModelProperty(value = "Password", example = "password1234")
	private String password;
}