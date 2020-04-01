package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Beacon;
import com.museumserver.entity.models.State;
import com.museumserver.entity.repositories.BeaconRepository;
import com.museumserver.entity.repositories.StateRepository;

@Service
public class BeaconServiceImpl implements BeaconService {

	public void setRepository(Object repository) {
		this.beaconRepository = (BeaconRepository) repository;
	}

	@Autowired
	private BeaconRepository beaconRepository;

	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<Beacon> getBeacons() {

		return (List<Beacon>) beaconRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteBeacon(long id) {

		beaconRepository.deleteById(id);

	}

	@Override
	public Beacon addBeacon(Beacon beacon) {

		return beaconRepository.save(beacon);

	}

	@Override
	public Beacon updateBeacon(Beacon beacon) {
		if (beaconRepository.existsById(beacon.getId())) {
			Beacon original = beaconRepository.findById(beacon.getId()).get();

			if (beacon.getId() != null)
				original.setId(beacon.getId());

			return beaconRepository.save(original);
		}
		return null;

	}

	@Override
	public Beacon getBeacon(Long id) {
		if (beaconRepository.existsById(id))
			return beaconRepository.findById(id).get();
		return null;
	}

	@Override
	public void activateBeacon(long id) {

		Beacon original = beaconRepository.findById(id).get();
		State active = stateRepository.findByName("ACTIVE");
		original.setState(active);
		beaconRepository.save(original);
	}

	@Override
	public void inactivateBeacon(long id) {

		Beacon original = beaconRepository.findById(id).get();
		State active = stateRepository.findByName("INACTIVE");
		original.setState(active);
		beaconRepository.save(original);
	}

}
