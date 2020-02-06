package com.museumserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.MediaModification;
import com.museumserver.entity.models.MediaModificationId;
import com.museumserver.entity.repositories.AdministratorRepository;
import com.museumserver.entity.repositories.MediaModificationRepository;
import com.museumserver.entity.repositories.MediaRepository;

@Service
public class MediaModificationServiceImpl implements MediaModificationService{

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private MediaModificationRepository mediaModificationRepository;
	
	
	@Override
	public List<MediaModification> getAllModifications() {
		
		return (List<MediaModification>)mediaModificationRepository.findAll();
	}

	@Override
	public List<MediaModification> getAdministratorModifications(Long administratorId) {
		List<MediaModification> result = new ArrayList<>();

		for (MediaModification modification : mediaModificationRepository.findAll()) {
			if (modification.getId().getAdministratorId() == administratorId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public List<MediaModification> getMediaModifications(Long mediaId) {
		List<MediaModification> result = new ArrayList<>();

		for (MediaModification modification : mediaModificationRepository.findAll()) {
			if (modification.getId().getMediaId() == mediaId)
				result.add(modification);
		}

		return result;
	}

	@Override
	public void addMediaModification(Long administratorId, Long mediaId, MediaModification modification) {
		if (administratorRepository.existsById(administratorId) && mediaRepository.existsById(mediaId)) {

			modification.setId(new MediaModificationId(administratorId, mediaId));

			mediaModificationRepository.save(modification);

		}
		
	}


}
