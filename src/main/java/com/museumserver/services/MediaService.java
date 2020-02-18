package com.museumserver.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.museumserver.entity.models.Media;

public interface MediaService {

	public Media getMedia(Long id);
	
	public List<Media> getMedias();

	public void deleteMedia(long id);

	public Media addMedia(Media media, MultipartFile file);

	public Media updateMedia(Media media);
}
