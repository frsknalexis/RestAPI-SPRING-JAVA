package com.dev.app.ws.model;

import java.io.Serializable;

public class UserResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024797539846979599L;

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;

	public UserResponse() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
}
