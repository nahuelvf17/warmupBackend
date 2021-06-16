package com.challenger.alkemy.api.warmup.models.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.challenger.alkemy.api.warmup.models.entity.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	
}