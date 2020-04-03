package com.museumserver.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
	
	List<User> findAllByOrderByIdAsc();

	@Query("SELECT user FROM app_users user WHERE user.state = (SELECT id FROM states WHERE name = 'ACTIVE') ORDER BY user.username")
	List<User> getActiveUsers();
	
	@Query("SELECT user FROM app_users user WHERE user.role = (SELECT id FROM roles WHERE name = 'ADMIN') ORDER BY user.username")
	List<User> getAdminUsers();
	
	@Query("SELECT user FROM app_users user WHERE user.role = (SELECT id FROM roles WHERE name != 'ADMIN') ORDER BY user.username")
	List<User> getNonAdminUsers();
	
}
