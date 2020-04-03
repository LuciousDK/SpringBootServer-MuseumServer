package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.ExhibitionModification;

public interface ExhibitionModificationService {

	public List<ExhibitionModification> getModificationsByUser(Long userId);

	public List<ExhibitionModification> getExhibitionModifications(Long exhibitionId);

	public void addExhibitionModification(Long userId, Long exhibitionId, ExhibitionModification modification);

}
