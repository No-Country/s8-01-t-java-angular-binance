package com.s8.binance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.security.entity.Role;
import com.s8.binance.security.enums.RoleName;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	
	public Optional<Role> findByRoleName(RoleName roleName);
}