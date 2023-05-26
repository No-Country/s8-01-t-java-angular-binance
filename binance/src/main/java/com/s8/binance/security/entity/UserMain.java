package com.s8.binance.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMain implements UserDetails {

	private String legalName;

	private String legalLastName;

	private String username;

	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public static UserMain build(User user) {
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolName().name()))
				.collect(Collectors.toList());
		return new UserMain(user.getLegalName(), user.getLegalLastName(), user.getUsername(), user.getEmail(),
				user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}