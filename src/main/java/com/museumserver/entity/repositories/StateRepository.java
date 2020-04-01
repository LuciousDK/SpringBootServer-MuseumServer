package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.State;

public interface StateRepository extends CrudRepository<State, Long> {
	State findByName(String name);
}
