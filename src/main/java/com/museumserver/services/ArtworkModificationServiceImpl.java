package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.ArtworkModification;
import com.museumserver.entity.models.ArtworkModificationId;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.ArtworkModificationRepository;
import com.museumserver.entity.repositories.ArtworkRepository;

@Service
public class ArtworkModificationServiceImpl implements ArtworkModificationService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArtworkRepository beaconRepository;
	
	@Autowired
	private ArtworkModificationRepository beaconModificationRepository;

	@Override
	public List<ArtworkModification> getModificationsByUser(Long userId) {
		List<ArtworkModification> result = new ArrayList<>();

		for (ArtworkModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getUserId() == userId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public List<ArtworkModification> getArtworkModifications(Long beaconId) {
		List<ArtworkModification> result = new ArrayList<>();

		for (ArtworkModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getArtworkId() == beaconId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public void addArtworkModification(Long userId, Long beaconId, ArtworkModification modification) {
		if (userRepository.existsById(userId) && beaconRepository.existsById(beaconId)) {

			modification.setId(new ArtworkModificationId(userId, beaconId));

			beaconModificationRepository.save(modification);

		}
		
	}


}
