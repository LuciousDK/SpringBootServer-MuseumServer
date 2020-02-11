package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Beacon;


public interface BeaconRepository extends CrudRepository<Beacon,Long>{
	List<Beacon> findAllByOrderByIdAsc();
}

