package com.challenger.alkemy.api.warmup.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.challenger.alkemy.api.warmup.models.repository.CategoriaRepository;

@Service
public class CategoriaImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public Iterable<Categoria> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Categoria> findCategoriaById(Long id) {
		return repository.findById(id);
	}

}
