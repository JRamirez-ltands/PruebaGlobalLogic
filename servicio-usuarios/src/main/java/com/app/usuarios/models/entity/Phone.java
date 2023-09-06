package com.app.usuarios.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="phones")
public class Phone implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( length = 30)
	private Integer number;
	
	@Column( length = 5)
	private String citycode;
	
	@Column( length = 5)
	private String countrycode;
	
	@OneToOne
	@JoinColumn(name="usuario_id")
	private User user;
	
	
	
	
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



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	private static final long serialVersionUID = 483083268704655888L;

}
