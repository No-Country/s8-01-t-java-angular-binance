package com.s8.binance.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.security.dto.JwtDto;
import com.s8.binance.security.dto.Login;
import com.s8.binance.security.dto.Register;
import com.s8.binance.security.service.UserDetailsServiceImpl;
import com.s8.binance.security.service.UserService;
import com.s8.binance.security.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	private final UserDetailsServiceImpl userDetailsService;

	@PostMapping("/add")
	public ResponseEntity<?> newUser(@Valid @RequestBody Register register) {
		userDetailsService.newUser(register);
		return new ResponseEntity<>(new Message("Usuario creado"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody Login login, BindingResult bindingResult) {
		return userDetailsService.login(login, bindingResult);
	}

	@PostMapping("/sendMail")
	public void emailVerification(@RequestParam String email, @RequestParam Integer num) {
		userService.emailVerification(email, num);
	}
}