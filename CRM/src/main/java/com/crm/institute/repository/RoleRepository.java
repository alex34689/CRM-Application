package com.crm.institute.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.institute.enttity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
