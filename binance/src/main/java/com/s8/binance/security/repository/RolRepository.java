package com.s8.binance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	public Optional<Rol> findByRolNombre(RolNombre rolNombre);
}