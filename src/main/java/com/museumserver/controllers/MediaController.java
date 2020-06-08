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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.museumserver.entity.models.DataViews;
import com.museumserver.entity.models.Media;
import com.museumserver.services.MediaService;

@RestController
@CrossOrigin(origins = "*")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping("/api/medias")
	@JsonView(DataViews.MediaRequest.class)
	public List<Media> getMedias() {
		return mediaService.getMedias();
	}

	@GetMapping("/api/media/{id}")
	@JsonView(DataViews.MediaRequest.class)
	public Media getMedia(@PathVariable("id") Long id) {
		return mediaService.getMedia(id);
	}
	
	@GetMapping("/api/media/title/{displayName}")
	@JsonView(DataViews.MediaRequest.class)
	public List<Media> getMediaByDisplayName(@PathVariable("displayName") String displayName) {
		return mediaService.getMediaByDisplayName(displayName);
	}

	@PostMapping("/api/media")
	public void addMedia(Media media, @RequestParam("file") MultipartFile file) {
		mediaService.addMedia(media, file);
	}

	@PutMapping("/api/media")
	public void updateMedia(Media media) {
		mediaService.updateMedia(media);
	}

	@DeleteMapping("/api/media")
	public void removeMedia(@RequestParam("id") Long id) {
		mediaService.deleteMedia(id);
	}

}
