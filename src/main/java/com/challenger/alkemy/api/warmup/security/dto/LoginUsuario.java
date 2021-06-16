package com.challenger.alkemy.api.warmup.security.dto;

import java.io.Serializable;

public class LoginUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5694837575779375453L;
	
	
	private String email;
    private String password;

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
