package com.crm.institute.service;

import com.crm.institute.enttity.UserF;

public interface UserService {

	public Iterable<UserF> getAllUsers();

	public UserF createUser(UserF user) throws Exception;

	public UserF getUserById(Long id) throws Exception;

	public UserF updateUser(UserF user) throws Exception;

	public void deleteUser(Long id) throws Exception;
}
