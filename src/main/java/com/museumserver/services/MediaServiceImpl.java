package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Media;
import com.museumserver.entity.repositories.MediaRepository;

@Service
public class MediaServiceImpl implements MediaService {

	public void setRepository(Object repository) {
		this.mediaRepository = (MediaRepository) repository;
	}

	@Autowired
	private MediaRepository mediaRepository;

	@Override
	public List<Media> getMedias() {

		return (List<Media>) mediaRepository.findAll();

	}

	@Override
	public void deleteMedia(long id) {

		mediaRepository.deleteById(id);

	}

	@Override
	public Media addMedia(Media media) {

		return mediaRepository.save(media);

	}

	@Override
	public Media updateMedia(Media media) {
		if (mediaRepository.existsById(media.getId())) {
			Media original = mediaRepository.findById(media.getId()).get();

			if (media.getDisplayName() != null)
				original.setDisplayName(media.getDisplayName());

			if (media.getFileName() != null)
				original.setFileName(media.getFileName());

			if (media.getExtension() != null)
				original.setExtension(media.getExtension());

			if (media.getFileType() != null)
				original.setFileType(media.getFileType());
			
			return mediaRepository.save(original);
		}
		return null;

	}

	@Override
	public Media getMedia(Long id) {
		if (mediaRepository.existsById(id))
			return mediaRepository.findById(id).get();
		return null;
	}

}
