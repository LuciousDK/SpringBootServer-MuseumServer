package com.museumserver.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.museumserver.entity.models.DataViews;
import com.museumserver.entity.models.Media;
import com.museumserver.services.MediaService;

@RestController
@CrossOrigin(origins = "*")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping("/medias")
	@JsonView(DataViews.MediaRequest.class)
	public List<Media> getMedias() {
		return mediaService.getMedias();
	}

	@GetMapping("/media/{id}")
	@JsonView(DataViews.MediaRequest.class)
	public Media getMedia(@PathVariable("id") Long id) {
		return mediaService.getMedia(id);
	}

	@PostMapping("/media")
	public void addMedia(Media media, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		mediaService.addMedia(media);
		if (file.isEmpty()) {

		}

		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(ClassLoader.getSystemResource("").getPath().replaceFirst("/", "")
					.replace("target/classes/", "src/main/resources/") + "img/" + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@PutMapping("/media")
	public void updateMedia(Media media) {
		mediaService.updateMedia(media);
	}

	@DeleteMapping("/media")
	public void removeMedia(@RequestParam("id") Long id) {
		mediaService.deleteMedia(id);
	}

}
