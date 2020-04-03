package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.BeaconModification;

public interface BeaconModificationService {

	public List<BeaconModification> getModificationsByUser(Long userId);

	public List<BeaconModification> getBeaconModifications(Long beaconId);

	public void addBeaconModification(Long userId, Long beaconId, BeaconModification modification);

}
