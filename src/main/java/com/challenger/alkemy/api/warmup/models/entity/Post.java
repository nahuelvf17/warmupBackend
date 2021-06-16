package com.challenger.alkemy.api.warmup.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotEmpty
	private String contenido;

	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	

	private String imagen;
	
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	private boolean deleted = Boolean.FALSE;
	
	@PrePersist
	public void PrePersist() {
		this.fechaCreacion= new Date();
	}
	
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Post)) {
			return false;
		}
		
		Post a  = (Post) obj;
		
		return this.id !=null && this.id.equals(a.getId());
	}
	
	
}