package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Beacon;

public interface BeaconService {

	public Beacon getBeacon(Long id);
	
	public List<Beacon> getBeacons();

	public void deleteBeacon(long id);

	public Beacon addBeacon(Beacon beacon);

	public Beacon updateBeacon(Beacon beacon);
}
