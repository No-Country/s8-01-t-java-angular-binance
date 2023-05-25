package com.s8.binance.controller;

import javax.validation.Valid;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.repository.IWalletRepository;
import com.s8.binance.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.s8.binance.security.dto.JwtDto;
import com.s8.binance.security.dto.Login;
import com.s8.binance.security.dto.Register;
import com.s8.binance.security.service.UserService;
import com.s8.binance.security.util.Message;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userService;
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	IWalletRepository repository;

	@PostMapping("/add")
	public ResponseEntity<?> newUser(@Valid @RequestBody Register register){
			userDetailsService.newUser(register);
			return new ResponseEntity<>(new Message("Usuario creado"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody Login login, BindingResult bindingResult) {
		return userDetailsService.login(login, bindingResult);
	}

	@PostMapping("/sendMail")
	public void emailVerification(@RequestParam String email, @RequestParam Integer num){
		userService.emailVerification(email, num);
	}
	@GetMapping("/all-wallets")
	private List<Wallet> all (){
		List <Wallet> list= repository.findAll();
		return list;
	}
}