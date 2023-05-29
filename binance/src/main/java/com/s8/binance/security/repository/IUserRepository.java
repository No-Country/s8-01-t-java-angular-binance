package com.s8.binance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.security.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String nombreUsuario);

	public boolean existsByUsername(String nombreUsuario);

	public boolean existsByEmail(String email);
}