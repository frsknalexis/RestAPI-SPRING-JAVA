package com.dev.app.ws.model;

import java.io.Serializable;

public class AddressRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6293164910186600629L;

	private String city;
	
	private String country;
	
	private String streetName;
	
	private String postalCode;
	
	private String type;

	public AddressRequestModel() {
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
