package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.UserModification;
import com.museumserver.entity.models.UserModificationId;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.UserModificationRepository;
@Service
public class UserModificationServiceImpl implements UserModificationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserModificationRepository beaconModificationRepository;

	@Override
	public List<UserModification> getModificationsByUser(Long userId) {
		List<UserModification> result = new ArrayList<>();

		for (UserModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getUserId() == userId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public List<UserModification> getUserModifications(Long modifiedUserId) {
		List<UserModification> result = new ArrayList<>();

		for (UserModification modification : beaconModificationRepository.findAll()) {
			if (modification.getId().getUserId() == modifiedUserId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public void addUserModification(Long userId, Long modifiedUserId, UserModification modification) {
		if (userRepository.existsById(userId) && userRepository.existsById(modifiedUserId)) {

			modification.setId(new UserModificationId(userId, modifiedUserId));

			beaconModificationRepository.save(modification);

		}
		
	}


}
