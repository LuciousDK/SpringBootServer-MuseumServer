package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
	Administrator findByUsername(String username);
	List<Administrator> findAllByOrderByIdAsc();
}
