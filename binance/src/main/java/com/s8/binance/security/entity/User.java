package com.s8.binance.security.entity;

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
import javax.validation.constraints.NotBlank;
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
	private Long id;

	@NotNull
	@Column(name = "EMAIL", unique = true)
	private String email;

	@NotNull
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Column(name = "USERNAME", unique = true)
	private String username;

	@NotNull
	@Column(name = "LEGAL_NAME")
	private String legalName;

	@NotNull
	@Column(name = "LEGAL_LAST_NAME")
	private String legalLastName;

	@NotNull
	@NotBlank
	@Column(name = "BIRTH_DATE")
	private String birthdate;

	@NotNull
	@Column(name = "FULL_ADDRESS")
	private String fullAddress;

	@NotNull
	@Column(name = "ZIP_CODE")
	private String zipCode;

	@NotNull
	@Column(name = "NATIONALITY")
	private String nationality;

	@NotNull
	@Column(name = "CITY")
	private String city;

	@NotNull
	@Column(name = "COUNTRY")
	private String country;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private Wallet wallet;

	@ManyToMany
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<>();
}
