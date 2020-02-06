package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.MediaModification;

public interface MediaModificationService {

	public List<MediaModification> getAllModifications();

	public List<MediaModification> getAdministratorModifications(Long administratorId);

	public List<MediaModification> getMediaModifications(Long mediaId);

	public void addMediaModification(Long administratorId, Long mediaId, MediaModification modification);

}
