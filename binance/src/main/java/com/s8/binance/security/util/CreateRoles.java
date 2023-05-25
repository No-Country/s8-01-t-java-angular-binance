package com.s8.binance.security.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.enums.RolName;
import com.s8.binance.security.repository.RolRepository;
import com.s8.binance.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	private RolService rolService;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public void run(String... args) throws Exception {

		List<Rol> rol = rolRepository.findAll();
		if (rol.isEmpty()) {
			Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
			Rol rolUser = new Rol(RolName.ROLE_USER);
			rolService.save(rolAdmin);
			rolService.save(rolUser);
		}


	}
}
