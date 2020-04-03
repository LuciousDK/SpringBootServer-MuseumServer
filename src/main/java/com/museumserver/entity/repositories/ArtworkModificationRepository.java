package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.ArtworkModification;
import com.museumserver.entity.models.ArtworkModificationId;

public interface ArtworkModificationRepository extends CrudRepository<ArtworkModification,ArtworkModificationId>{

}
