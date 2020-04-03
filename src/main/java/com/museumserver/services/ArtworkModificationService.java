package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.ArtworkModification;

public interface ArtworkModificationService {

	public List<ArtworkModification> getModificationsByUser(Long userId);

	public List<ArtworkModification> getArtworkModifications(Long artworkId);

	public void addArtworkModification(Long userId, Long artworkId, ArtworkModification modification);

}
