package com.museumserver.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.museumserver.entity.models.Media;

public interface MediaService {

	public Media getMedia(Long id);
	
	public List<Media> getMedias();
	
	public Page<Media> getMediasPaginated(int page, int size);

	public void deleteMedia(long id);

	public Media addMedia(Media media, MultipartFile file);

	public Media updateMedia(Media media);
	
	public List<Media> getMediaByDisplayName (String name);
}
