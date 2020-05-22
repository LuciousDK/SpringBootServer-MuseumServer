package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}
