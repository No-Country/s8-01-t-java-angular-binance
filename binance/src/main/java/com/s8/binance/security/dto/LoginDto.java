package com.s8.binance.security.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User login information")
public class LoginDto {

	@NotBlank
	@ApiModelProperty(example = "antonelaroccuzzo")
	private String username;

	@NotBlank
	@ApiModelProperty(example = "password1234")
	private String password;
}