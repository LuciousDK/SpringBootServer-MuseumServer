package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Media;


public interface MediaRepository extends CrudRepository<Media,Long>{
	List<Media> findAllByOrderByIdAsc();
}

