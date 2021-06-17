package com.challenger.alkemy.api.warmup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.challenger.alkemy.api.warmup.models.entity.Post;
import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.models.repository.CategoriaRepository;
import com.challenger.alkemy.api.warmup.models.repository.PostRepository;
import com.challenger.alkemy.api.warmup.models.repository.UsuarioRepository;


class RepositoryMockTest {
	@Autowired
    Optional<Post> post;

    PostRepository postRepositoryMock = Mockito.mock(PostRepository.class);
    
    CategoriaRepository categoriaRepositoryMock = Mockito.mock(CategoriaRepository.class);

    UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
    
	
	@BeforeEach
	void setUp() {				
		Categoria mockCategoria = new Categoria();
		mockCategoria.setId(Long.valueOf(1));
		mockCategoria.setDescripcion("Matematica");
		
		Categoria mockCategoria2 = new Categoria();
		mockCategoria2.setId(Long.valueOf(2));
		mockCategoria2.setDescripcion("Historia");
		
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
		
		Post mockPost2 = new Post();
		
		mockPost2.setTitulo("Matematica Moderna 2");
		mockPost2.setContenido("Matematica America");
		mockPost2.setImagen("https://image.freepik.com/vector-gratis/ilustracion-dia-internacional-jazz-instrumentos-musicales_23-2148865774.jpg");
		mockPost2.setCategoria(mockCategoria);
		mockPost2.setUsuario(mockUsuario);
		
		Post mockPost3 = new Post();
		
		mockPost2.setTitulo("Historia Americana");
		mockPost2.setContenido("Historia Americana");
		mockPost2.setImagen("https://image.freepik.com/vector-gratis/ilustracion-dia-internacional-jazz-instrumentos-musicales_23-2148865774.jpg");
		mockPost2.setCategoria(mockCategoria2);
		mockPost2.setUsuario(mockUsuario);
		
        Mockito.when(postRepositoryMock.findById(Long.valueOf(1))).thenReturn(Optional.of(mockPost));
        
        List<Post> mockPostList = new ArrayList<>();

        
        mockPostList.add(mockPost);
        mockPostList.add(mockPost2);
        mockPostList.add(mockPost3);


        Mockito.when(postRepositoryMock.findAll()).thenReturn(mockPostList);

	}
	
	@Test
	void getPostWithValidPostCode() {
        Post postFound = postRepositoryMock.findById(Long.valueOf(1)).get();
        Assertions.assertEquals("Matematica Moderna",postFound.getTitulo());
    }
	
	
	@Test
	public void getPostAllSuccess() throws Exception {
		 List<Post> postAll = (List<Post>) postRepositoryMock.findAll();
         assertEquals(3, postAll.size());
	}
	
	@Test
	public void getPostAllFail() throws Exception {
		 List<Post> postAll = (List<Post>) postRepositoryMock.findAll();
         assertFalse(postAll.size()==5);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
	}

}
