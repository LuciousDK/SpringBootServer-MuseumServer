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
import com.museumserver.entity.models.User;
import com.museumserver.entity.models.DataViews;
import com.museumserver.services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/api/users")
	@JsonView(DataViews.UsersRequest.class)
	public List<User> getUsers() {
		return userService.getActiveUsers();
	}

	@GetMapping("/api/user/{id}")
	@JsonView(DataViews.UsersRequest.class)
	public User getUser(@PathVariable("id") Long id) {
		return userService.getUser(id);
	}

	@PostMapping("/api/user")
	public void addUser(User user) {
		userService.addUser(user);
	}

	@PutMapping("/api/user")
	public void updateUser(User user) {
		userService.updateUser(user);
	}

	@DeleteMapping("/api/user")
	public void removeUser(@RequestParam("id") Long id) {
		userService.deleteUser(id);
	}
	

}
