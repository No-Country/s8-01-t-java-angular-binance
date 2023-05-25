package com.s8.binance.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.enums.RolName;
import com.s8.binance.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	@Autowired
	private RolRepository rolRepository;
	public Optional<Rol> getByRolName(RolName rolName) {
		return rolRepository.findByRolName(rolName);
	}
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}