package com.crm.institute.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.institute.enttity.UserF;

@Repository
public interface UserRepository extends CrudRepository<UserF, Long> {

	public Optional<UserF> findByUsername(String username);
	
}
