package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.ExhibitionModification;
import com.museumserver.entity.models.ExhibitionModificationId;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.ExhibitionModificationRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;

@Service
public class ExhibitionModificationServiceImpl implements ExhibitionModificationService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExhibitionRepository exhibitionRepository;
	
	@Autowired
	private ExhibitionModificationRepository exhibitionModificationRepository;

	@Override
	public List<ExhibitionModification> getModificationsByUser(Long userId) {
		List<ExhibitionModification> result = new ArrayList<>();

		for (ExhibitionModification modification : exhibitionModificationRepository.findAll()) {
			if (modification.getId().getUserId() == userId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public List<ExhibitionModification> getExhibitionModifications(Long exhibitionId) {
		List<ExhibitionModification> result = new ArrayList<>();

		for (ExhibitionModification modification : exhibitionModificationRepository.findAll()) {
			if (modification.getId().getExhibitionId() == exhibitionId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public void addExhibitionModification(Long userId, Long exhibitionId, ExhibitionModification modification) {
		if (userRepository.existsById(userId) && exhibitionRepository.existsById(exhibitionId)) {

			modification.setId(new ExhibitionModificationId(userId, exhibitionId));

			exhibitionModificationRepository.save(modification);

		}
		
	}


}
