package com.challenger.alkemy.api.warmup.services;

import java.util.Optional;

import com.challenger.alkemy.api.warmup.models.entity.Categoria;

public interface CategoriaService {

	public Iterable<Categoria> findAll();
	public Optional<Categoria> findCategoriaById(Long id);
}
