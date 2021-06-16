package com.challenger.alkemy.api.warmup.models.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.challenger.alkemy.api.warmup.models.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	
}