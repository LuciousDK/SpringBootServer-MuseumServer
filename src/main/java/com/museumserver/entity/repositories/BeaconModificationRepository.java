package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.BeaconModification;
import com.museumserver.entity.models.BeaconModificationId;

public interface BeaconModificationRepository extends CrudRepository<BeaconModification,BeaconModificationId>{

}
