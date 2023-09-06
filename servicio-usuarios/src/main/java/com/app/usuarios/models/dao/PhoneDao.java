package com.app.usuarios.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.usuarios.models.entity.Phone;
import com.app.usuarios.models.entity.User;

public interface PhoneDao extends CrudRepository<Phone, Long> {

	
	@Query("select p from Phone p where p.user =?1")
	List<Phone> findAllPhonesByUser(User user);

}
