package com.app.usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.usuarios.models.entity.Phone;
import com.app.usuarios.models.entity.Session;
import com.app.usuarios.models.entity.User;
import com.app.usuarios.models.service.IUserService;
import com.app.usuarios.requests.LoginRequest;
import com.app.usuarios.requests.UserRequest;
import com.app.usuarios.responses.ErrorResponse;
import com.app.usuarios.responses.LoginResponse;
import com.app.usuarios.responses.UserResponse;
import com.app.usuarios.utils.UtilsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private IUserService userService;

	
	@Autowired
	private UtilsService utils;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<Object> userCreate(@RequestBody UserRequest userRequest) throws Exception{
		try {
			utils.validateUserRequest(userRequest);
			UserResponse userResponses = userService.createUser(userRequest);
			return new ResponseEntity<Object>(userResponses, HttpStatus.CREATED);
		}catch (Exception e) {
			ErrorResponse errorResponse = utils.getError(e.getMessage());
			return new ResponseEntity<Object>(errorResponse, errorResponse.getHttpStatus());
		}
		
	}  
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) throws Exception{				
		try {
			utils.validateLoginRequest(loginRequest);
			LoginResponse loginResponse = userService.login(loginRequest);
			return new ResponseEntity<Object>(loginResponse, HttpStatus.ACCEPTED);
		}catch (Exception e) {
			ErrorResponse errorResponse = utils.getError(e.getMessage());
			return new ResponseEntity<Object>(errorResponse, errorResponse.getHttpStatus());
		}
        
		
	}  
	
	
	@GetMapping("/user")
	public ResponseEntity<Object> getUser() throws Exception{
	
		try {
			List<User> users =  userService.getAllUser();
			return new ResponseEntity<Object>(users, HttpStatus.OK);
		}catch (Exception e) {
			ErrorResponse errorResponse = utils.getError(e.getMessage());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_GATEWAY);
		}
        
		
	}  
	
	@GetMapping("/session")
	public ResponseEntity<Object> getSession() throws Exception{
	
		try {
			List<Session> sessions =  userService.getAllSession();
			return new ResponseEntity<Object>(sessions, HttpStatus.OK);
		}catch (Exception e) {
			ErrorResponse errorResponse = utils.getError(e.getMessage());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_GATEWAY);
		}
        
		
	}  
	
	@GetMapping("/phone")
	public ResponseEntity<Object> getPhones() throws Exception{
	
		try {
			List<Phone> sessions =  userService.getAllPhones();
			return new ResponseEntity<Object>(sessions, HttpStatus.OK);
		}catch (Exception e) {
			ErrorResponse errorResponse = utils.getError(e.getMessage());
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_GATEWAY);
		}
        
		
	}  

}
