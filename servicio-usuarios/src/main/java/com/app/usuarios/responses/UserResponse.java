package com.app.usuarios.responses;

import java.util.Date;
import java.util.List;

import com.app.usuarios.models.entity.Phone;
import com.app.usuarios.models.entity.User;

public class UserResponse {
	
	private Long id;
	private Date created;
	private boolean isActive;

	
	
	
	
	public UserResponse() {
	}
	
	public UserResponse(User user, List<Phone> listPhonesIn) {
		this.id = user.getId();
		this.created = user.getCreated();
		this.isActive = user.getIsActive();
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	
	

}
