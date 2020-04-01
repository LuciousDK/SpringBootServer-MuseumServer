package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.AppUser;

public interface AppUserService {

	public AppUser getAppUser(Long id);
	
	public List<AppUser> getAppUsers();

	public void deleteAppUser(long id);

	public AppUser addAppUser(AppUser administrator);

	public AppUser updateAppUser(AppUser administrator);

	public void activateAppUser(String username);

	public void inactivateAppUser(String username);
	
	public void setRole(String username, String roleName);
}
