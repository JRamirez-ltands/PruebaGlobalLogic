package com.app.usuarios.models.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.usuarios.jwt.JwtUtil;
import com.app.usuarios.models.dao.PhoneDao;
import com.app.usuarios.models.dao.SessionDao;
import com.app.usuarios.models.dao.UserDao;
import com.app.usuarios.models.entity.Phone;
import com.app.usuarios.models.entity.Session;
import com.app.usuarios.models.entity.User;
import com.app.usuarios.requests.LoginRequest;
import com.app.usuarios.requests.UserRequest;
import com.app.usuarios.responses.LoginResponse;
import com.app.usuarios.responses.PhoneResponse;
import com.app.usuarios.responses.UserResponse;
import jakarta.transaction.Transactional;



@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private PhoneDao phoneDao;
	
	@Autowired
	private JwtUtil jwt;

	@Override
	public UserResponse createUser(UserRequest userRequest) throws Exception {
		try {
			User userOld = userDao.finfByEmail(userRequest.getEmail());
			if(userOld != null) {
				throw new Exception("Usuario ya existe && 400");
			}
			User user = new User(userRequest);
			userDao.save(user);
			Phone phone = new Phone();
			List<Phone> listPhonesNew = new ArrayList<>();
			for (int i = 0; i < userRequest.getPhones().size(); i++) {
				phone = userRequest.getPhones().get(i);
				phone.setUser(user);
				listPhonesNew.add(phone);
			}
			if(listPhonesNew.size()>0) {
				phoneDao.saveAll(listPhonesNew);
			}
			UserResponse userResponse = new UserResponse(user, listPhonesNew);
			return userResponse;
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) throws Exception {
		try {
			User user = userDao.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
			Date dateUltimaSession = null;
			if(user != null) {
				Session ultimaSession = sessionDao.findSessionActive(user);
				if(ultimaSession != null) {
					ultimaSession.setIsActive(false);
					sessionDao.updateEstadoSessionPorId(ultimaSession.getId(), false);
					dateUltimaSession = new Date(ultimaSession.getCreated().getTime());
				}
				Session session = new Session(user, jwt.generaTokens());
				sessionDao.save(session);
				List<Phone> phones = phoneDao.findAllPhonesByUser(user);
				LoginResponse loginResponse = new LoginResponse(user, dateUltimaSession, session, obtienePhonesResponse(phones));
				return loginResponse;
			} else {
				throw new Exception("Usuario o password incorrectos o no existe && 400");
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	

	private List<PhoneResponse> obtienePhonesResponse(List<Phone> phones) {
		List<PhoneResponse> listPhoneResponse = new ArrayList<>();
		PhoneResponse phoneResponse = new PhoneResponse();
		for (int i = 0; i < phones.size(); i++) {
			phoneResponse = new PhoneResponse(phones.get(i));
			listPhoneResponse.add(phoneResponse);
		}
		return listPhoneResponse;
	}

	@Override
	public List<User> getAllUser() {
		return (List<User>)userDao.findAll();
	}

	@Override
	public List<Session> getAllSession() {
		return (List<Session>)sessionDao.findAll();
	}

	@Override
	public List<Phone> getAllPhones() {
		return (List<Phone>)phoneDao.findAll();
	}

}
