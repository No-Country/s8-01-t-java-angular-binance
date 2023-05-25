package com.s8.binance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String nombreUsuario);

	public boolean existsByUsername(String nombreUsuario);

	public boolean existsByEmail(String email);
}