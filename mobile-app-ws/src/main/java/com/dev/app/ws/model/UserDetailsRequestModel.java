package com.dev.app.ws.model;

import java.io.Serializable;

public class UserDetailsRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7501410546486598452L;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

	public UserDetailsRequestModel() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
