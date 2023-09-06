package com.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.usuarios.models.entity.Session;
import com.app.usuarios.models.entity.User;

public interface SessionDao extends CrudRepository<Session, Long> {
	
	@Query("select s from Session s where s.user =?1 and s.isActive=true")
	Session findSessionActive(User user);
	
	@Modifying
	@Query("update Session s SET s.isActive =?2 WHERE s.id =?1")
	void updateEstadoSessionPorId(Long id, boolean estado);

}
