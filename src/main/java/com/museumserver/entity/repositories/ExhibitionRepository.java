package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Exhibition;


public interface ExhibitionRepository extends CrudRepository<Exhibition,Long>{
	
	List<Exhibition> findAllByOrderByIdAsc();
	
	@Query("SELECT exhibition FROM exhibitions exhibition WHERE exhibition.state = (SELECT id FROM states WHERE name = 'ACTIVE') ORDER BY exhibition.id")
	List<Exhibition> getActiveExhibitions();
}

