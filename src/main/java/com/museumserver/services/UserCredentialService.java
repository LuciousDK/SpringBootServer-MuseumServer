package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.User;
import com.museumserver.entity.repositories.UserRepository;

@Service
public class UserCredentialService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
		return userDetails;
	}

}
