package com.museumserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.museumserver.entity.models.Beacon;
import com.museumserver.entity.models.DataViews;
import com.museumserver.services.BeaconService;

@RestController
@CrossOrigin(origins = "*")
public class BeaconController {

	@Autowired
	private BeaconService beaconService;

	@GetMapping("/api/beacons")
	@JsonView(DataViews.BeaconsRequest.class)
	public List<Beacon> getBeacons() {
		return beaconService.getActiveBeacons();
	}

	@GetMapping("/api/beacon/{id}")
	@JsonView(DataViews.BeaconsRequest.class)
	public Beacon getBeacon(@PathVariable("id") Long id) {
		return beaconService.getBeacon(id);
	}

	@PostMapping("/api/beacon")
	public void addBeacon(Beacon beacon) {
		beaconService.addBeacon(beacon);
	}

	@PutMapping("/api/beacon")
	public void updateBeacon(Beacon beacon) {
		beaconService.updateBeacon(beacon);
	}

	@DeleteMapping("/api/beacon")
	public void removeBeacon(@RequestParam("id") Long id) {
		beaconService.deleteBeacon(id);
	}
	

}
