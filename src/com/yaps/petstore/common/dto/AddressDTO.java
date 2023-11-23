/*
 * Created on 3 déc. 2005
 * AddressDTO.java
 */
package com.yaps.petstore.common.dto;

/**
 * @author Veronique
 * AddressDTO.java
 */
public final class AddressDTO implements DataTransfertObject {

		// ==============================
		// Attributes
		// ==============================
	private String _country;
	private String _street1;
	private String _street2;
	private String _city;
	private String _state;
	private String _zipcode;
		
	//=================================
	// = 	Getters and Setters
	//================================
	public void setCountry(final String country) {
		_country = country;
	}
	public String getCountry() {
		return _country;
	}

	public void setStreet1(final String street1) {
		_street1= street1;
		
	}
	public String getStreet1() {
		return _street1;
	}

	
	public void setStreet2(final String street2) {		
		_street2 = street2;
	}

	
	public String getStreet2() {
		return _street2;
	}

	
	public void setCity(final String city) {
		_city = city;
	}

	
	public String getCity() {
		return _city;
	}

	
	public void setState(final String state) {		
		_state = state;
	}

	
	public String getState() {
		return _state;
	}

	
	public void setZipcode(final String zipcode) {
		_zipcode = zipcode;
		
	}

	
	public String getZipcode() {
		
		return _zipcode;
	}

}
