package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.AppUser;

public interface AdministratorService {

	public AppUser getAdministrator(Long id);
	
	public List<AppUser> getAdministrators();

	public void deleteAdministrator(long id);

	public AppUser addAdministrator(AppUser administrator);

	public AppUser updateAdministrator(AppUser administrator);
}
