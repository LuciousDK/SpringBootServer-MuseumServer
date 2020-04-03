package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.User;

public interface UserService {

	public User getUser(Long id);
	
	public List<User> getActiveUsers();
	
	public List<User> getAllUsers();
	
	public List<User> getAdminUsers();
	
	public List<User> getNonAdminUsers();

	public void deleteUser(long id);

	public User addUser(User user);

	public User updateUser(User user);

	public void activateUser(String username);

	public void inactivateUser(String username);
	
	public void setRole(String username, String roleName);
}
