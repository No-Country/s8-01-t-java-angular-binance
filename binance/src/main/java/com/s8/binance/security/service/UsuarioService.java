package com.s8.binance.security.service;



import com.s8.binance.security.entity.Usuario;
import com.s8.binance.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Optional<Usuario> getByUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}

	public Boolean existsByUsuario(String nombreUsuario){
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

	public Boolean existsByEmail(String email){
		return usuarioRepository.existsByEmail(email);
	}

	public void save(Usuario usuario){
		usuarioRepository.save(usuario);
	}


}