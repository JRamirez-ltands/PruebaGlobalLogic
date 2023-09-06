package com.app.usuarios.responses;

import java.util.Date;
import java.util.List;

import com.app.usuarios.models.entity.Session;
import com.app.usuarios.models.entity.User;

public class LoginResponse {
	
	
	private Long id;
	private Date created;
	private Date lastLogin;
	private String token;
	private boolean isActive;
	private String name;
	private String email;
	private String password;
	private List<PhoneResponse> phones;
	

	public LoginResponse() {
	}


	public LoginResponse(User user, Date lastDateSessionIn, Session newSessionIn, List<PhoneResponse> phonesIn) {
		this.id = user.getId();
		this.created = user.getCreated();
		this.token = newSessionIn.getToken();
		this.isActive = user.getIsActive();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.lastLogin = lastDateSessionIn;
		this.phones = phonesIn;
		
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public List<PhoneResponse> getPhones() {
		return phones;
	}


	public void setPhones(List<PhoneResponse> phones) {
		this.phones = phones;
	}
	
	

}
