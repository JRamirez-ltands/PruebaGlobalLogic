package com.app.usuarios.models.service;

import java.util.List;

import com.app.usuarios.models.entity.Phone;
import com.app.usuarios.models.entity.Session;
import com.app.usuarios.models.entity.User;
import com.app.usuarios.requests.LoginRequest;
import com.app.usuarios.requests.UserRequest;
import com.app.usuarios.responses.LoginResponse;
import com.app.usuarios.responses.UserResponse;



public interface IUserService {
	
	UserResponse createUser(UserRequest userRequest) throws Exception;
	LoginResponse login(LoginRequest loginRequest) throws Exception;
	List<User> getAllUser();
	List<Session> getAllSession();
	List<Phone> getAllPhones(); 

}
