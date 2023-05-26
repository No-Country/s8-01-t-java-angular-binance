package com.s8.binance.security.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.s8.binance.model.entity.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@NotNull
	private String legalName;

	@NotNull
	private String legalLastName;

	@NotNull
	private String birthdate;

	@NotNull
	private String fullAddress;

	@NotNull
	private String postalCode;

	@NotNull
	private String nationality;
	
	@NotNull
	private String city;

	@NotNull
	private String country;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private Wallet wallet;

	@NotNull
	@ManyToMany
	@JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
}
