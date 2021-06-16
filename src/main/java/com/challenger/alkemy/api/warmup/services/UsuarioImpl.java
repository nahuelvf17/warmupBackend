package com.challenger.alkemy.api.warmup.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.models.repository.UsuarioRepository;

@Service
public class UsuarioImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> findUsuarioByEmail(String userName) {
		return repository.findUsuarioByEmail(userName);
	}

	@Override
	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) throws Exception{
		
		if(usuario.getPassword().length()<6) {
    		throw new Exception("Debe especificar una contraseña de almenos 6 caracteres");
    	}
    	
    	final Optional<Usuario> checkUsuario = findUsuarioByEmail(usuario.getEmail());
    	if(checkUsuario.isPresent()) {
    		throw new Exception("Ya existe un usuario registrado con este email");
    	}
		
		return repository.save(usuario);
	}
}
