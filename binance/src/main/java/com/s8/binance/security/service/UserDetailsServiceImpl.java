package com.s8.binance.security.service;

import javax.transaction.Transactional;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.security.dto.JwtDto;
import com.s8.binance.security.dto.Login;
import com.s8.binance.security.dto.Register;
import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.enums.RolName;
import com.s8.binance.security.jwt.JwtProvider;
import com.s8.binance.security.repository.UserRepository;
import com.s8.binance.security.util.Message;
import com.s8.binance.service.impl.EmailService;
import com.s8.binance.service.impl.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.s8.binance.security.entity.User;
import com.s8.binance.security.entity.UsuarioMain;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que convierte la clase usuario en un UsuarioMain
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolService rolService;
	@Autowired
	WalletService walletService;

	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	AuthenticationManager authenticationManager;

	public UserDetailsServiceImpl() {
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUser(username).get();
		return UsuarioMain.build(user);
	}
	public User newUser(Register register) {
		User user = new User(register.getName(), register.getUsername(),
				register.getEmail(), passwordEncoder.encode(register.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.ROLE_USER).orElseThrow());
		if (register.getRoles().contains("admin"))
			roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
		user.setRoles(roles);
		Wallet walet = new Wallet();
		user.setWallet(walet);
		userService.save(user);
		walletService.createWallet(user);
		return user;
	}
	public ResponseEntity<JwtDto> login(Login login, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("Campos mal"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(),
						login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}
