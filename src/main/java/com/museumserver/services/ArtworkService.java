package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Artwork;

public interface ArtworkService {

	public Artwork getArtwork(Long id);
	
	public List<Artwork> getAllArtworks();
	
	public List<Artwork> getActiveArtworks();
	
	public List<Artwork> getInactiveArtworks();

	public void deleteArtwork(long id);

	public void toggleArtwork(long id);

	public void addArtwork(Artwork artwork, Long exhibitionId);

	public void addMedia(Long artworkId, Long mediaId);

	public void removeMedia(Long artworkId, Long mediaId);

	public Artwork updateArtwork(Artwork artwork, Long exhibitionId);
}
