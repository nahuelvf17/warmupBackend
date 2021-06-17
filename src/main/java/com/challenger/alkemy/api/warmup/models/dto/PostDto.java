package com.challenger.alkemy.api.warmup.models.dto;


import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class PostDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7172776178525410305L;

	private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String contenido;
	
	private int categoriaId;

	private String imagen;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
