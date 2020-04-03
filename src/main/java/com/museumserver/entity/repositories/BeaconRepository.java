package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Beacon;


public interface BeaconRepository extends CrudRepository<Beacon,Long>{
	
	List<Beacon> findAllByOrderByIdAsc();
	
	@Query("SELECT beacon FROM beacons beacon WHERE beacon.state = (SELECT id FROM states WHERE name = 'ACTIVE') ORDER BY beacon.id")
	List<Beacon> getActiveBeacons();
}

