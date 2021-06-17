package com.challenger.alkemy.api.warmup;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.challenger.alkemy.api.warmup.commons.CommonsUtils;
import com.challenger.alkemy.api.warmup.controllers.PostController;
import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.challenger.alkemy.api.warmup.models.entity.Post;
import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.models.repository.CategoriaRepository;
import com.challenger.alkemy.api.warmup.models.repository.PostRepository;
import com.challenger.alkemy.api.warmup.models.repository.UsuarioRepository;
import com.challenger.alkemy.api.warmup.services.PostService;

class controllerTest {

	
	@Autowired
    Optional<Post> post;
	
	@Autowired
	CommonsUtils commonsUtil;
	
	@Autowired
	PostService postService;

    PostRepository postRepositoryMock = Mockito.mock(PostRepository.class);
    
    CategoriaRepository categoriaRepositoryMock = Mockito.mock(CategoriaRepository.class);

    UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
    
    

    @Autowired
    PostController postController = new PostController();
	
	@BeforeEach
	void setUp() {
		System.out.println("aca es before");
		
		Categoria mockCategoria = new Categoria();
		mockCategoria.setId(Long.valueOf(1));
		mockCategoria.setDescripcion("Matematica");
		
		Usuario mockUsuario = new Usuario();
		mockUsuario.setId(Long.valueOf(1));
		mockUsuario.setEmail("nahuel@gmail.com");
		mockUsuario.setPassword("password");

		Post mockPost = new Post();
		
		mockPost.setTitulo("Matematica Moderna");
		mockPost.setContenido("Matematica de todo el mundo");
		mockPost.setImagen("https://image.freepik.com/vector-gratis/ilustracion-dia-internacional-jazz-instrumentos-musicales_23-2148865774.jpg");
		mockPost.setCategoria(mockCategoria);
		mockPost.setUsuario(mockUsuario);
		
        Mockito.when(postRepositoryMock.findById(Long.valueOf(1))).thenReturn(Optional.of(mockPost));

	}
	
	@Test
	void getPostWithValidPostCode() {
        ResponseEntity<Post> respuestaServicio;
        Post postFound = postRepositoryMock.findById(Long.valueOf(1)).get();
        Assertions.assertEquals("Matematica Modernar",postFound.getTitulo());
    }

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("aca es after");

	}

	@Test
	void test() {
		System.out.println("aca es test principal");

	}

}
