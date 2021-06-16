package com.challenger.alkemy.api.warmup.services;

import java.util.Optional;

import com.challenger.alkemy.api.warmup.models.entity.Usuario;


public interface UsuarioService {

	public Iterable<Usuario> findAll();
			
	public Usuario save(Usuario usuario) throws Exception;
		
	public Optional<Usuario> findUsuarioByEmail(String userName);
	
}
