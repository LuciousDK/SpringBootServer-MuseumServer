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

import com.museumserver.entity.models.Administrator;
import com.museumserver.services.AdministratorService;

@RestController
@CrossOrigin(origins = "*")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@GetMapping("/administrators")
	public List<Administrator> getAdministrators() {
		return administratorService.getAdministrators();
	}

	@GetMapping("/administrator/{id}")
	public Administrator getAdministrator(@PathVariable("id") Long id) {
		return administratorService.getAdministrator(id);
	}

	@PostMapping("/administrator")
	public void addAdministrator(Administrator administrator) {
		administratorService.addAdministrator(administrator);
	}

	@PutMapping("/administrator")
	public void updateAdministrator(Administrator administrator) {
		administratorService.updateAdministrator(administrator);
	}

	@DeleteMapping("/administrator")
	public void removeAdministrator(@RequestParam("id") Long id) {
		administratorService.deleteAdministrator(id);
	}
	

}
