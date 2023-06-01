package com.s8.binance.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.security.dto.LoginDto;
import com.s8.binance.security.dto.RegisterDto;
import com.s8.binance.security.entity.User;
import com.s8.binance.security.service.IUserService;
import com.s8.binance.security.service.impl.UserDetailsServiceImpl;
import com.s8.binance.security.util.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Api(tags = "Authentication", description = "Register and login for Binance users.")
public class AuthController {

	private final IUserService userService;

	private final UserDetailsServiceImpl userDetailsService;

	@PostMapping("/register")
	@ApiOperation("Register a user in Binance")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterDto register) {
		userDetailsService.newUser(register);
		return new ResponseEntity<>(new Message("User succesfully created"), HttpStatus.CREATED);
	}

	// @PostMapping("/login")
	// @ApiOperation("Login for a Binance user")
	// public ResponseEntity<?> login(@Valid @RequestBody LoginDto login,
	// BindingResult bindingResult) {
	// return userDetailsService.login(login, bindingResult);
	// }

	// MOMENTANEOOOO
	@PostMapping("/login")
	@ApiOperation("Login for a Binance user")
	public ResponseEntity<UserDetailsResponseDto> login(@Valid @RequestBody LoginDto login) {
		User user = userService.getByUsername(login.getUsername()).orElseThrow();
		UserDetailsResponseDto response = userService.getUserById(user.getId());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/email")
	@ApiOperation("Send a verification code via email")
	public void emailVerification(@RequestParam String email, @RequestParam Integer num) {
		userService.emailVerification(email, num);
	}
}