package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.MediaModification;
import com.museumserver.entity.models.MediaModificationId;

public interface MediaModificationRepository extends CrudRepository<MediaModification,MediaModificationId>{

}
