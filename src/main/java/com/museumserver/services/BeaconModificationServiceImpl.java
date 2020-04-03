package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.BeaconModification;
import com.museumserver.entity.models.BeaconModificationId;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.BeaconModificationRepository;
import com.museumserver.entity.repositories.BeaconRepository;

@Service
public class BeaconModificationServiceImpl implements BeaconModificationService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BeaconRepository beaconRepository;
	
	@Autowired
	private BeaconModificationRepository beaconModificationRepository;

	@Override
	public List<BeaconModification> getModificationsByUser(Long userId) {
		List<BeaconModification> result = new ArrayList<>();

		for (BeaconModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getUserId() == userId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public List<BeaconModification> getBeaconModifications(Long beaconId) {
		List<BeaconModification> result = new ArrayList<>();

		for (BeaconModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getBeaconId() == beaconId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public void addBeaconModification(Long userId, Long beaconId, BeaconModification modification) {
		if (userRepository.existsById(userId) && beaconRepository.existsById(beaconId)) {

			modification.setId(new BeaconModificationId(userId, beaconId));

			beaconModificationRepository.save(modification);

		}
		
	}


}
