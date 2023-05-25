package com.s8.binance.security.entity;

import com.s8.binance.model.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
public class User {
	@Id

		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
		@NotNull
		private String name;
		@NotNull
		@Column(unique = true)
		private String username;
		@NotNull
		@Column(unique = true)
		private String email;
		@NotNull
		private String password;


		@OneToOne(  fetch = FetchType.EAGER , cascade = CascadeType.ALL)
		@JoinColumn( name ="id_user")
		Wallet wallet;

		@NotNull
		@ManyToMany
		@JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "id_user"),
				inverseJoinColumns = @JoinColumn(name = "rol_id"))
		private Set<Rol> roles = new HashSet<>();

		public User() {
		}

		public User(@NotNull String name,
					@NotNull String username,
					@NotNull String email,
					@NotNull String password) {
			this.name = name;
			this.username = username;
			this.email = email;
			this.password = password;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUser() {
			return username;
		}

		public void setUser(String user) {
			this.username = user;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<Rol> getRoles() {
			return roles;
		}

		public void setRoles(Set<Rol> roles) {
			this.roles = roles;
		}
	}

