package com.s8.binance.security.service.impl;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.s8.binance.model.entity.Wallet;
import com.s8.binance.security.dto.JwtDto;
import com.s8.binance.security.dto.LoginDto;
import com.s8.binance.security.dto.RegisterDto;
import com.s8.binance.security.entity.Role;
import com.s8.binance.security.entity.User;
import com.s8.binance.security.entity.UserMain;
import com.s8.binance.security.enums.RoleName;
import com.s8.binance.security.jwt.JwtProvider;
import com.s8.binance.security.util.Message;

import lombok.RequiredArgsConstructor;

@Service @Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;
	private final JwtProvider jwtProvider;
	@Autowired
	AuthenticationManager authenticationManager;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUsername(username).get();
		return UserMain.build(user);
	}
	public User newUser(RegisterDto register) {
			Set<Role> roles = new HashSet<>();
			roles.add(roleService.getByRoleName(RoleName.ROLE_USER).orElseThrow());

			User user = User.builder()
					.email(register.getEmail())
					.password(passwordEncoder.encode(register.getPassword()))
					.username(register.getUsername())
					.legalName(register.getLegalName())
					.legalLastName(register.getLegalLastName())
					.birthdate(register.getBirthdate())
					.fullAddress(register.getFullAddress())
					.zipCode(register.getZipCode())
					.nationality(register.getNationality())
					.city(register.getCity())
					.country(register.getCountry())
					.roles(roles)
					.build();

			// if (register.getRoles().contains("ADMIN")) {
			// roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
			// user.setRoles(roles);
			// }

			user.setWallet(new Wallet());
			userService.save(user);
			return user;
	}

	public ResponseEntity<?> login(LoginDto login, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("400 Bad Request"), HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(),
						login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtDto jwtDto = JwtDto.builder()
				.token(jwtProvider.generateToken(authentication))
				.username(userDetails.getUsername())
				.authorities(userDetails.getAuthorities())
				.build();

		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}
