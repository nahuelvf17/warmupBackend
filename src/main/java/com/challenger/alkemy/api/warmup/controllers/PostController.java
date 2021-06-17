package com.challenger.alkemy.api.warmup.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.challenger.alkemy.api.warmup.commons.CommonsUtils;
import com.challenger.alkemy.api.warmup.models.dto.PostDto;
import com.challenger.alkemy.api.warmup.models.dto.PostResponseDto;
import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.challenger.alkemy.api.warmup.models.entity.Post;
import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.services.PostService;


@RequestMapping("/")
@RestController
public class PostController {
	
	@Autowired
	private CommonsUtils commonsUtilService;
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/posts")
	public ResponseEntity<?> crear(@Valid PostDto postDto, BindingResult result) {
				
		if(result.hasErrors()) {
			return commonsUtilService.validar(result);
		}

		Categoria categoriaDb;
		Usuario usuarioDb;
		
		try {
			categoriaDb= commonsUtilService.validarCategoria(Long.valueOf(postDto.getCategoriaId()));
			usuarioDb= commonsUtilService.validarUsuario();
			commonsUtilService.validarUrl(postDto.getImagen());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		Post postEntity = new Post();
		
		postEntity.setTitulo(postDto.getTitulo());
		postEntity.setContenido(postDto.getContenido());
		postEntity.setImagen(postDto.getImagen());
		postEntity.setCategoria(categoriaDb);
		postEntity.setUsuario(usuarioDb);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postEntity));
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<?> modificar(@Valid PostDto postDto, BindingResult result, @PathVariable Long id) {

		if(result.hasErrors()) {
			return commonsUtilService.validar(result);
		}

		Optional<Post> postDb = postService.findById(id);
		if(!postDb.isPresent()) {
			return ResponseEntity.badRequest().body("No se encontro el post");
		}
		
		Categoria categoriaDb;
		Usuario usuarioDb;
		
		try {
			categoriaDb= commonsUtilService.validarCategoria(Long.valueOf(postDto.getCategoriaId()));
			usuarioDb= commonsUtilService.validarUsuario();
			commonsUtilService.validarUrl(postDto.getImagen());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		Post postUpdated = postDb.get();

		postUpdated.setTitulo(postDto.getTitulo());
		postUpdated.setContenido(postDto.getContenido());
		postUpdated.setImagen(postDto.getImagen());
		postUpdated.setCategoria(categoriaDb);
		postUpdated.setUsuario(usuarioDb);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postUpdated));
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> borrar(@PathVariable Long id ){
		Optional<Post> postDb = postService.findById(id);
		if(!postDb.isPresent()) {
			return ResponseEntity.badRequest().body("No se encontro el post, no se podra eliminar");
		}
		
		postService.delete(postDb.get());
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<?> mostrar(@PathVariable Long id ){
		Optional<Post> postDb = postService.findById(id);
		if(!postDb.isPresent()) {
			return ResponseEntity.badRequest().body("No se encontro el post");
		}
		
		ModelMapper modelMapper = new ModelMapper();
		
		PostResponseDto postDto= modelMapper.map(postDb.get(), PostResponseDto.class);
		
		return ResponseEntity.ok().body(postDto);
	}	
	
	@GetMapping("/posts")
	public ResponseEntity<?> filtrar(@RequestParam(value="title", required=false) String titulo, 
									 @RequestParam(value="category", required=false) Long categoriaId){
		
		List<Post> posts = (List<Post>) postService.findByFilter(titulo, categoriaId);
		
		ModelMapper modelMapper = new ModelMapper();
		List<PostResponseDto> postsDto = posts
				  .stream()
				  .map(post -> modelMapper.map(post, PostResponseDto.class))
				  .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(postsDto);
	}
}
