package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.DataCount;
import com.museumserver.entity.models.Media;


public interface MediaRepository extends CrudRepository<Media,Long>{
	List<Media> findAllByOrderByIdAsc();
	
	
	@Query("SELECT new com.museumserver.entity.models.DataCount(v.fileType ,COUNT(v)) FROM media AS v GROUP BY v.fileType")
	List<DataCount> getCountOfType();
}