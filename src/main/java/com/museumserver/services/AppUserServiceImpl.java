package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.AppUser;
import com.museumserver.entity.models.Role;
import com.museumserver.entity.models.State;
import com.museumserver.entity.repositories.AppUserRepository;
import com.museumserver.entity.repositories.RoleRepository;
import com.museumserver.entity.repositories.StateRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

	public void setRepository(Object repository) {
		this.appUserRepository = (AppUserRepository) repository;
	}

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<AppUser> getAppUsers() {

		return (List<AppUser>) appUserRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteAppUser(long id) {

		appUserRepository.deleteById(id);

	}

	@Override
	public AppUser addAppUser(AppUser appUser) {
		String password = appUser.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		appUser.setPassword(password);

		return appUserRepository.save(appUser);

	}

	@Override
	public AppUser updateAppUser(AppUser appUser) {
		if (appUserRepository.existsById(appUser.getId())) {
			AppUser original = appUserRepository.findById(appUser.getId()).get();

			if (appUser.getEmail() != null)
				original.setEmail(appUser.getEmail());

			if (appUser.getFirstName() != null)
				original.setFirstName(appUser.getFirstName());

			if (appUser.getLastName() != null)
				original.setLastName(appUser.getLastName());

			if (appUser.getPassword() != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String password = passwordEncoder.encode(appUser.getPassword());
				original.setPassword(password);
			}
			return appUserRepository.save(original);
		}
		return null;

	}

	@Override
	public AppUser getAppUser(Long id) {
		if (appUserRepository.existsById(id))
			return appUserRepository.findById(id).get();
		return null;
	}

	@Override
	public void activateAppUser(String username) {
		AppUser original = appUserRepository.findByUsername(username);
		State state = stateRepository.findByName("ACTIVE");
		original.setState(state);
		appUserRepository.save(original);
	}

	@Override
	public void inactivateAppUser(String username) {
		AppUser original = appUserRepository.findByUsername(username);
		State state = stateRepository.findByName("INACTIVE");
		original.setState(state);
		appUserRepository.save(original);
		
	}

	@Override
	public void setRole(String username, String roleName) {
		AppUser original = appUserRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		original.setRole(role);
		appUserRepository.save(original);
		
	}


}
