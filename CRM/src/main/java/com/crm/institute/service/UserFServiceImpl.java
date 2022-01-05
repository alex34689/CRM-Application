package com.crm.institute.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.institute.Exception.UsernameOrIdNotFound;
import com.crm.institute.dto.ChangePassword;
import com.crm.institute.enttity.UserF;
import com.crm.institute.repository.UserRepository;

@Service
public class UserFServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

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
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			String encodeConfirmPassword = bCryptPasswordEncoder.encode(user.getConfirmPassword());
			user.setPassword(encodePassword);
			user.setConfirmPassword(encodeConfirmPassword);
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public UserF getUserById(Long id) throws UsernameOrIdNotFound {
		return repository.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe"));
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		UserF user = getUserById(id);
		repository.delete(user);

	}

	@Override
	public UserF changePassword(ChangePassword form) throws Exception {
		UserF user = getUserById(form.getId());
		if (!isLoggedUserADMIN() && !user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception("Current Password invalido.");
		}
		if (user.getPassword().equals(form.getNewPassword())) {
			throw new Exception("Nuevo password debe de ser diferente al actual");
		}
		if (!form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception("Nuevo Password y Current Password no coinciden");
		}

		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		String encodeConfirmPassword = bCryptPasswordEncoder.encode(form.getConfirmPassword());
		user.setPassword(encodePassword);
		user.setConfirmPassword(encodeConfirmPassword);
		repository.save(user);
		return null;
	}

	public boolean isLoggedUserADMIN() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;

			loggedUser.getAuthorities().stream().filter(x -> "ADMIN".equals(x.getAuthority())).findFirst().orElse(null);

		}
		return loggedUser != null ? true : false;
	}

}
