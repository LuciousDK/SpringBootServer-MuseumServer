package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.UserModification;
import com.museumserver.entity.models.UserModificationId;

public interface UserModificationRepository extends CrudRepository<UserModification,UserModificationId>{

}
