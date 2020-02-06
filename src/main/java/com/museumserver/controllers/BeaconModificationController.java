package com.museumserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.museumserver.entity.models.BeaconModification;
import com.museumserver.services.BeaconModificationService;

@RestController
@CrossOrigin(origins = "*")
public class BeaconModificationController {

	@Autowired
	BeaconModificationService beaconModificationService;

	@GetMapping("/beacon/modifications")
	public List<BeaconModification> getAllModifications() {
		return beaconModificationService.getAllModifications();
	}

	@GetMapping("/beacon/modifications/administrator/{administratorId}")
	public List<BeaconModification> getAdministratorModifications(@PathVariable("administratorId") Long administratorId) {
		return beaconModificationService.getAdministratorModifications(administratorId);
	}

	@GetMapping("/beacon/modifications/{beaconId}")
	public List<BeaconModification> getBeaconModifications(@PathVariable("beaconId") Long beaconId) {
		return beaconModificationService.getAdministratorModifications(beaconId);
	}

	@PostMapping("/beacon/newModification")
	public void addBeaconModification(@RequestParam("administratorId") Long administratorId, @RequestParam("beaconId") Long beaconId,
			BeaconModification modification) {

		beaconModificationService.addBeaconModification(administratorId, beaconId, modification);
	}

}
