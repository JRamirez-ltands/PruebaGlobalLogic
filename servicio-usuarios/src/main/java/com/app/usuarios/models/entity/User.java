package com.app.usuarios.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.app.usuarios.requests.UserRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="users")
public class User implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 30)
	private String name;
	
	@Column(unique = true, length = 20)
	private String email;
	
	@Column(length = 100)
	private String password;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Phone> phones;
	
	private Date created;
	
	@Column( length = 20)
	private Boolean isActive;
	
	
	
	public User() {
	}
	
		
	public User(UserRequest userRequest) {
		this.name = userRequest.getName();
		this.email = userRequest.getEmail();
		this.password = userRequest.getPassword();
		this.created = new Date();
		this.isActive = true;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	



	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}




	private static final long serialVersionUID = -8712114554967678812L;

	

}
