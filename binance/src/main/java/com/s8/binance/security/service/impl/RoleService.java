package com.s8.binance.security.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.s8.binance.security.entity.Role;
import com.s8.binance.security.enums.RoleName;
import com.s8.binance.security.repository.IRoleRepository;
import com.s8.binance.security.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

	private final IRoleRepository roleRepository;

	public Optional<Role> getByRoleName(RoleName roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}
}