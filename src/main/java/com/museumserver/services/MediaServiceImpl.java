package com.museumserver.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.museumserver.entity.models.Media;
import com.museumserver.entity.repositories.MediaRepository;

@Service
public class MediaServiceImpl implements MediaService {


	@Autowired
	private MediaRepository mediaRepository;

	@Override
	public Page<Media> getMedias(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return mediaRepository.findAll(pageable);

	}

	@Override
	public Page<Media> getMediasByName(int page, int size, String name){
		Pageable pageable = PageRequest.of(page, size);
		return mediaRepository.findByDisplayName(pageable, name);
	}

	@Override
	public void deleteMedia(long id) {
		Media original = mediaRepository.findById(id).get();
		String location = "src/main/resources/";
		if (original.getFileType().equals("image")) {
			location += "img/images/";
		}
		if (original.getFileType().equals("video")) {
			location += "video/";
		}
		if (original.getFileType().equals("audio")) {
			location += "audio/";
		}
		location += original.getFileName() + "." + original.getExtension();
		new File(location).delete();

		mediaRepository.deleteById(id);

	}

	@Override
	public Media addMedia(Media media, MultipartFile file) {

		if (file.isEmpty()) {

		}

		try {

			byte[] bytes = file.getBytes();

			Path path = null;

			if (media.getFileType().equals("image")) {
				path = Paths.get(ClassLoader.getSystemResource("").getPath().replaceFirst("/", "").replace(
						"target/classes/", "src/main/resources/") + "img/images/" + file.getOriginalFilename());
			}

			if (media.getFileType().contentEquals("video")) {
				path = Paths.get(ClassLoader.getSystemResource("").getPath().replaceFirst("/", "")
						.replace("target/classes/", "src/main/resources/") + "video/" + file.getOriginalFilename());
			}

			if (media.getFileType().contentEquals("audio")) {
				path = Paths.get(ClassLoader.getSystemResource("").getPath().replaceFirst("/", "")
						.replace("target/classes/", "src/main/resources/") + "audio/" + file.getOriginalFilename());
			}

			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

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
