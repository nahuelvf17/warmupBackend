package com.challenger.alkemy.api.warmup.models.dto;

import java.io.Serializable;
import java.util.Date;

import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PostResponseDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5309465720403616469L;


	private Long id;
	
	private String titulo;
	
	private String imagen;
	
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"}, allowSetters=true)
	private Categoria categoria;

	private Date fechaCreacion;

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
