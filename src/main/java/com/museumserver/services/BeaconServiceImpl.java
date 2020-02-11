package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Beacon;
import com.museumserver.entity.repositories.BeaconRepository;

@Service
public class BeaconServiceImpl implements BeaconService {

	public void setRepository(Object repository) {
		this.beaconRepository = (BeaconRepository) repository;
	}

	@Autowired
	private BeaconRepository beaconRepository;

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

			if (beacon.getMac() != null)
				original.setMac(beacon.getMac());


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

}
