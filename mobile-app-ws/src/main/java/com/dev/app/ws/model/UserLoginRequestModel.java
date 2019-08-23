package com.dev.app.ws.model;

import java.io.Serializable;

public class UserLoginRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7355368923085772800L;

	private String email;
	
	private String password;

	public UserLoginRequestModel() {
		
	}

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
