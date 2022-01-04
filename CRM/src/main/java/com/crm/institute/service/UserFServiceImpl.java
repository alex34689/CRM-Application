package com.crm.institute.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.institute.enttity.UserF;
import com.crm.institute.repository.UserRepository;

@Service
public class UserFServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public Iterable<UserF> getAllUsers() {
		return repository.findAll();
	}

	private boolean checkUsernameAvailable(UserF user) throws Exception {
		Optional<UserF> userFound = repository.findByUsername(user.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPassworValid(UserF user) throws Exception {
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm Password es Obligatorio");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public UserF createUser(UserF user) throws Exception {
		if (checkUsernameAvailable(user) && checkPassworValid(user)) {
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public UserF getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe"));
	}

	@Override
	public UserF updateUser(UserF fromUser) throws Exception {
		UserF toUser = getUserById(fromUser.getId());
		mapuser(fromUser, toUser);
		return repository.save(toUser);
	}

	/**
	 * Map everythin but the password
	 * 
	 * @param from
	 * @param to
	 */
	protected void mapuser(UserF from, UserF to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
		to.setConfirmPassword(from.getConfirmPassword());
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		UserF user = getUserById(id);
		repository.delete(user);

	}

}
