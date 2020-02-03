package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Artwork;

public interface ArtworkService {

	public Artwork getArtwork(Long id);
	
	public List<Artwork> getArtworks();

	public void deleteArtwork(long id);

	public Artwork addArtwork(Artwork artwork);

	public Artwork updateArtwork(Artwork artwork);
}
