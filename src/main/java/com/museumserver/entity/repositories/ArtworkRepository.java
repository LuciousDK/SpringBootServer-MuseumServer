package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Artwork;


public interface ArtworkRepository extends CrudRepository<Artwork,Long>{
	
	List<Artwork> findAllByOrderByIdAsc();
	
	@Query("SELECT artwork FROM artworks artwork WHERE artwork.state = (SELECT id FROM states WHERE name  = 'ACTIVE') ORDER BY artwork.id")
	List<Artwork> getActiveArtworks();
	
}

