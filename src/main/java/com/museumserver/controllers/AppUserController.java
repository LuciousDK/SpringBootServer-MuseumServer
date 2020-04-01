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
import com.museumserver.services.AppUserService;

@RestController
@CrossOrigin(origins = "*")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@GetMapping("/appUsers")
	@JsonView(DataViews.AppUsersRequest.class)
	public List<AppUser> getAppUsers() {
		return appUserService.getAppUsers();
	}

	@GetMapping("/appUser/{id}")
	@JsonView(DataViews.AppUsersRequest.class)
	public AppUser getAppUser(@PathVariable("id") Long id) {
		return appUserService.getAppUser(id);
	}

	@PostMapping("/appUser")
	public void addAppUser(AppUser appUser) {
		appUserService.addAppUser(appUser);
	}

	@PutMapping("/appUser")
	public void updateAppUser(AppUser appUser) {
		appUserService.updateAppUser(appUser);
	}

	@DeleteMapping("/appUser")
	public void removeAppUser(@RequestParam("id") Long id) {
		appUserService.deleteAppUser(id);
	}
	

}
