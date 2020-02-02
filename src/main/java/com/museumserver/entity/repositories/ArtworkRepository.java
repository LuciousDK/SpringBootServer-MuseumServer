package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Artwork;


public interface ArtworkRepository extends CrudRepository<Artwork,Long>{

}

