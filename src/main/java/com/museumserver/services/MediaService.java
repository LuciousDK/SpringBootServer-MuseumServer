package com.museumserver.services;


import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.museumserver.entity.models.Media;

public interface MediaService {

	public Media getMedia(Long id);
	
	public Page<Media> getMedias(int page, int size);
	
	public Page<Media> getMediasByName(int page, int size, String Name);

	public void deleteMedia(long id);

	public Media addMedia(Media media, MultipartFile file);

	public Media updateMedia(Media media);
}
