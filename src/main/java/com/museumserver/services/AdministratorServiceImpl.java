package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Administrator;
import com.museumserver.entity.repositories.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	public void setRepository(Object repository) {
		this.administratorRepository = (AdministratorRepository) repository;
	}

	@Autowired
	private AdministratorRepository administratorRepository;

	@Override
	public List<Administrator> getAdministrators() {

		return (List<Administrator>) administratorRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteAdministrator(long id) {

		administratorRepository.deleteById(id);

	}

	@Override
	public Administrator addAdministrator(Administrator administrator) {
		String password = administrator.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		administrator.setPassword(password);

		return administratorRepository.save(administrator);

	}

	@Override
	public Administrator updateAdministrator(Administrator administrator) {
		if (administratorRepository.existsById(administrator.getId())) {
			Administrator original = administratorRepository.findById(administrator.getId()).get();

			if (administrator.getEmail() != null)
				original.setEmail(administrator.getEmail());

			if (administrator.getFirstName() != null)
				original.setFirstName(administrator.getFirstName());

			if (administrator.getLastName() != null)
				original.setLastName(administrator.getLastName());

			if (administrator.getPassword() != null)
				original.setPassword(administrator.getPassword());

			return administratorRepository.save(original);
		}
		return null;

	}

	@Override
	public Administrator getAdministrator(Long id) {
		if (administratorRepository.existsById(id))
			return administratorRepository.findById(id).get();
		return null;
	}

}
