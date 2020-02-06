package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.BeaconModification;

public interface BeaconModificationService {

	public List<BeaconModification> getAllModifications();

	public List<BeaconModification> getAdministratorModifications(Long administratorId);

	public List<BeaconModification> getBeaconModifications(Long beaconId);

	public void addBeaconModification(Long administratorId, Long beaconId, BeaconModification modification);

}
