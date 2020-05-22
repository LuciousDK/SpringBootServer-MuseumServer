package com.museumserver.entity.repositories;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.ExhibitionModification;
import com.museumserver.entity.models.ExhibitionModificationId;

public interface ExhibitionModificationRepository extends CrudRepository<ExhibitionModification,ExhibitionModificationId >{

}
