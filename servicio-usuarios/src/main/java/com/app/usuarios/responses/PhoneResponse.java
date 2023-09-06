package com.app.usuarios.responses;

import com.app.usuarios.models.entity.Phone;

public class PhoneResponse {
	
	private Integer number;
	
	private String citycode;
	
	private String countrycode;
	
	
	public PhoneResponse() {

	}

	public PhoneResponse(Phone phone) {
		this.number=phone.getNumber();
		this.citycode=phone.getCitycode();
		this.countrycode=phone.getCountrycode();
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	
	
	

}
