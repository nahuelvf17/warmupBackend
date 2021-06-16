package com.challenger.alkemy.api.warmup.services;

import java.util.Optional;

import com.challenger.alkemy.api.warmup.models.entity.Post;

public interface PostService {

	public Iterable<Post> findAll();
	public Optional<Post> findById(Long id);
	public Post save(Post post);	
	public void deleteById(Long id) throws Exception;
	public void delete(Post post);
	public Iterable<Post> findByFilter(String titulo, Long categoryId);
}
