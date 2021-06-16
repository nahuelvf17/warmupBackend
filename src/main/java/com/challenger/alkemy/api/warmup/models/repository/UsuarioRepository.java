package com.challenger.alkemy.api.warmup.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.challenger.alkemy.api.warmup.models.entity.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	public Optional<Usuario> findUsuarioByEmail(String userName);
}
