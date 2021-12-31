package com.crm.institute.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.institute.enttity.UserF;
import com.crm.institute.repository.UserRepository;

@Service
public class UserFServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<UserF> getAllUsers() {
		return repository.findAll();
	}
	
	private boolean checkUsernameAvailable(UserF user) throws Exception {
		Optional<UserF> userFound = repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPassworValid(UserF user) throws Exception {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public UserF createUser(UserF user) throws Exception {
		if(checkUsernameAvailable(user) && checkPassworValid(user)) {
			user = repository.save(user);
		}
		return user;
	}
	
}
