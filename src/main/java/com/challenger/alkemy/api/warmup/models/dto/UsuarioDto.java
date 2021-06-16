package com.challenger.alkemy.api.warmup.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UsuarioDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062358618627167210L;
	
    @Email()
    private String email;
	
	@NotEmpty
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String userEmail) {
		this.email = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
