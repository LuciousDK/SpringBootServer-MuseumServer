package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.AppUser;

public interface AdministratorRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	List<AppUser> findAllByOrderByIdAsc();
}
