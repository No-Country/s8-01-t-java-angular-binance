package com.s8.binance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s8.binance.security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);

	public boolean existsByNombreUsuario(String nombreUsuario);

	public boolean existsByEmail(String email);
}