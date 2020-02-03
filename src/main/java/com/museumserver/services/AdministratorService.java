package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Administrator;

public interface AdministratorService {

	public Administrator getAdministrator(Long id);
	
	public List<Administrator> getAdministrators();

	public void deleteAdministrator(long id);

	public Administrator addAdministrator(Administrator administrator);

	public Administrator updateAdministrator(Administrator administrator);
}
