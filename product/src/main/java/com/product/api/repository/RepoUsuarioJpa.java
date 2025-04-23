package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entity.Usuario;

import java.util.Optional;


public interface RepoUsuarioJpa extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByCorreo(String correo);
	Optional<Usuario> findByUsername(String username);
	
	
}