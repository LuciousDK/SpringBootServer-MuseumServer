package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.AppUser;
import com.museumserver.entity.repositories.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	public void setRepository(Object repository) {
		this.administratorRepository = (AdministratorRepository) repository;
	}

	@Autowired
	private AdministratorRepository administratorRepository;

	@Override
	public List<AppUser> getAdministrators() {

		return (List<AppUser>) administratorRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteAdministrator(long id) {

		administratorRepository.deleteById(id);

	}

	@Override
	public AppUser addAdministrator(AppUser administrator) {
		String password = administrator.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		administrator.setPassword(password);

		return administratorRepository.save(administrator);

	}

	@Override
	public AppUser updateAdministrator(AppUser administrator) {
		if (administratorRepository.existsById(administrator.getId())) {
			AppUser original = administratorRepository.findById(administrator.getId()).get();

			if (administrator.getEmail() != null)
				original.setEmail(administrator.getEmail());

			if (administrator.getFirstName() != null)
				original.setFirstName(administrator.getFirstName());

			if (administrator.getLastName() != null)
				original.setLastName(administrator.getLastName());

			if (administrator.getPassword() != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String password = passwordEncoder.encode(administrator.getPassword());
				original.setPassword(password);
			}
			return administratorRepository.save(original);
		}
		return null;

	}

	@Override
	public AppUser getAdministrator(Long id) {
		if (administratorRepository.existsById(id))
			return administratorRepository.findById(id).get();
		return null;
	}

}
