package com.crm.institute.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.institute.enttity.UserF;

@Repository
public interface UserRepository extends CrudRepository<UserF, Long> {

	
}
