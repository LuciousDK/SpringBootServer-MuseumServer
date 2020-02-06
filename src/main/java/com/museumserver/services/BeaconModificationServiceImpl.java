package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.BeaconModification;
import com.museumserver.entity.models.BeaconModificationId;
import com.museumserver.entity.repositories.AdministratorRepository;
import com.museumserver.entity.repositories.BeaconModificationRepository;
import com.museumserver.entity.repositories.BeaconRepository;

@Service
public class BeaconModificationServiceImpl implements BeaconModificationService{

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private BeaconRepository beaconRepository;
	
	@Autowired
	private BeaconModificationRepository beaconModificationRepository;
	
	
	@Override
	public List<BeaconModification> getAllModifications() {
		
		return (List<BeaconModification>)beaconModificationRepository.findAll();
	}

	@Override
	public List<BeaconModification> getAdministratorModifications(Long administratorId) {
		List<BeaconModification> result = new ArrayList<>();

		for (BeaconModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getAdministratorId() == administratorId)
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
	public void addBeaconModification(Long administratorId, Long beaconId, BeaconModification modification) {
		if (administratorRepository.existsById(administratorId) && beaconRepository.existsById(beaconId)) {

			modification.setId(new BeaconModificationId(administratorId, beaconId));

			beaconModificationRepository.save(modification);

		}
		
	}


}
