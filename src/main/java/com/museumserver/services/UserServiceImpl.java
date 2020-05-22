package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.User;
import com.museumserver.entity.models.Role;
import com.museumserver.entity.models.State;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.RoleRepository;
import com.museumserver.entity.repositories.StateRepository;

@Service
public class UserServiceImpl implements UserService {

	public void setRepository(Object repository) {
		this.userRepository = (UserRepository) repository;
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> getAllUsers() {

		return (List<User>) userRepository.findAllByOrderByIdAsc();

	}

	@Override
	public List<User> getActiveUsers() {
		
		return (List<User>) userRepository.getActiveUsers();
		
	}

	@Override
	public List<User> getAdminUsers() {
		
		return (List<User>) userRepository.getAdminUsers();

	}

	@Override
	public List<User> getNonAdminUsers() {
		
		return (List<User>) userRepository.getNonAdminUsers();

	}

	@Override
	public void deleteUser(long id) {

		userRepository.deleteById(id);

	}

	@Override
	public User addUser(User user) {
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		user.setPassword(password);

		return userRepository.save(user);

	}

	@Override
	public User updateUser(User user) {
		if (userRepository.existsById(user.getId())) {
			User original = userRepository.findById(user.getId()).get();

			if (user.getEmail() != null)
				original.setEmail(user.getEmail());

			if (user.getFirstName() != null)
				original.setFirstName(user.getFirstName());

			if (user.getLastName() != null)
				original.setLastName(user.getLastName());

			if (user.getPassword() != null) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String password = passwordEncoder.encode(user.getPassword());
				original.setPassword(password);
			}
			return userRepository.save(original);
		}
		return null;

	}

	@Override
	public User getUser(Long id) {
		if (userRepository.existsById(id))
			return userRepository.findById(id).get();
		return null;
	}

	@Override
	public void activateUser(String username) {
		User original = userRepository.findByUsername(username);
		State state = stateRepository.findByName("ACTIVE");
		original.setState(state);
		userRepository.save(original);
	}

	@Override
	public void inactivateUser(String username) {
		User original = userRepository.findByUsername(username);
		State state = stateRepository.findByName("INACTIVE");
		original.setState(state);
		userRepository.save(original);

	}

	@Override
	public void setRole(String username, String roleName) {
		User original = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		original.setRole(role);
		userRepository.save(original);

	}

}
