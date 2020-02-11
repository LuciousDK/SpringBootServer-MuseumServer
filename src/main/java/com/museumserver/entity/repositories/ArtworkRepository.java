package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Artwork;


public interface ArtworkRepository extends CrudRepository<Artwork,Long>{
	List<Artwork> findAllByOrderByIdAsc();
}

