package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.museumserver.entity.models.Media;


public interface MediaRepository extends PagingAndSortingRepository<Media,Long>{
	List<Media> findAllByOrderByIdAsc();
	
	@Query("SELECT file FROM media file WHERE file.displayName = ?1 ORDER BY file.id ASC")
	List<Media> getMediaByDisplayName(String displayName);
	
}