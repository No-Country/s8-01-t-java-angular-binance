package com.s8.binance.security.dto;
import java.util.Collection;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import lombok.Builder.Default;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
	private String token;
	@Default
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	public JwtDto(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
}