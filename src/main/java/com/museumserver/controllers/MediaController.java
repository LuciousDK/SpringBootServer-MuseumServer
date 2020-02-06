package com.museumserver.controllers;

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

import com.museumserver.entity.models.Media;
import com.museumserver.services.MediaService;

@RestController
@CrossOrigin(origins = "*")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping("/medias")
	public List<Media> getMedias() {
		return mediaService.getMedias();
	}

	@GetMapping("/media/{id}")
	public Media getMedia(@PathVariable("id") Long id) {
		return mediaService.getMedia(id);
	}

	@PostMapping("/media")
	public void addMedia(Media media) {
		mediaService.addMedia(media);
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
