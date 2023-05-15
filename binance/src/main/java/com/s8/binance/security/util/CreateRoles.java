package com.s8.binance.security.util;


import com.s8.binance.security.entity.Rol;
import com.s8.binance.security.enums.RolNombre;
import com.s8.binance.security.repository.RolRepository;
import com.s8.binance.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.List;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;
	@Autowired
	RolRepository rolRepository;
	@Override
	public void run(String... args) throws Exception {

		List<Rol> rol = rolRepository.findAll();
		if (rol.isEmpty())
		{
			Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
			Rol rolUser = new Rol(RolNombre.ROLE_USER);
			rolService.save(rolAdmin);
			rolService.save(rolUser);
		}

	}
}

