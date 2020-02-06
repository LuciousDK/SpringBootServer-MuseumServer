package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
	Administrator findByUsername(String username);
}
