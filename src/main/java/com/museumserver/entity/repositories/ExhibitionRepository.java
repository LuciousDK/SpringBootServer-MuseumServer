package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Exhibition;


public interface ExhibitionRepository extends CrudRepository<Exhibition,Long>{

}
