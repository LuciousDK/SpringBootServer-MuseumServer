package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Media;

public interface MediaService {

	public Media getMedia(Long id);
	
	public List<Media> getMedias();

	public void deleteMedia(long id);

	public Media addMedia(Media media);

	public Media updateMedia(Media media);
}
