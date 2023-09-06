package com.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.app.usuarios.models.entity.User;


public interface UserDao extends CrudRepository<User, Long> {

	@Query("select u from User u where u.email =?1 and password=?2")
	User findByEmailAndPassword(String userEmail, String password);

	@Query("select u from User u where u.email =?1")
	User finfByEmail(String email);

}
