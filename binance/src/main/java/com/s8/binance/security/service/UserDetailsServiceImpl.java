package com.s8.binance.security.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.security.dto.JwtDto;
import com.s8.binance.security.dto.Login;
import com.s8.binance.security.dto.Register;
import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.entity.User;
import com.s8.binance.security.entity.UserMain;
import com.s8.binance.security.enums.RolName;
import com.s8.binance.security.jwt.JwtProvider;
import com.s8.binance.security.util.Message;

import lombok.NoArgsConstructor;

/**
 * Clase que convierte la clase usuario en un UsuarioMain
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolService rolService;

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUser(username).get();
		return UserMain.build(user);
	}

	public User newUser(Register register) {
		User user = User.builder()
		.email(register.getEmail())
		.password(passwordEncoder.encode(register.getPassword()))
		.country(register.getCountry())
		.username(register.getUsername())
		.legalName(register.getLegalName())
		.legalLastName(register.getLegalLastName())
		.birthdate(register.getBirthdate())
		.fullAddress(register.getFullAddress())
		.postalCode(register.getPostalCode())
		.nationality(register.getNationality())
		.city(register.getCity())
		.build();
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolName(RolName.ROLE_USER).orElseThrow());
		if (register.getRoles().contains("admin"))
			roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
		user.setRoles(roles);
		
		user.setWallet(new Wallet());
		userService.save(user);		
		return user;
	}

	public ResponseEntity<JwtDto> login(Login login, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Message("Bad request"), HttpStatus.BAD_REQUEST);
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
