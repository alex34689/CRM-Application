package com.crm.institute.service;

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

	
}
