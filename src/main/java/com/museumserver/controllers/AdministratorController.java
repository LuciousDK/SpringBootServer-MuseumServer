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
import com.museumserver.entity.models.AppUser;
import com.museumserver.entity.models.DataViews;
import com.museumserver.services.AdministratorService;

@RestController
@CrossOrigin(origins = "*")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping("/administrators")
	@JsonView(DataViews.AppUsersRequest.class)
	public List<AppUser> getAdministrators() {
		return administratorService.getAdministrators();
	}

	@GetMapping("/administrator/{id}")
	@JsonView(DataViews.AppUsersRequest.class)
	public AppUser getAdministrator(@PathVariable("id") Long id) {
		return administratorService.getAdministrator(id);
	}

	@PostMapping("/administrator")
	public void addAdministrator(AppUser administrator) {
		administratorService.addAdministrator(administrator);
	}

	@PutMapping("/administrator")
	public void updateAdministrator(AppUser administrator) {
		administratorService.updateAdministrator(administrator);
	}

	@DeleteMapping("/administrator")
	public void removeAdministrator(@RequestParam("id") Long id) {
		administratorService.deleteAdministrator(id);
	}
	

}
