package com.museumserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.museumserver.entity.models.MediaModification;
import com.museumserver.services.MediaModificationService;

@RestController
@CrossOrigin(origins = "*")
public class MediaModificationController {

	@Autowired
	MediaModificationService mediaModificationService;

	@GetMapping("/media/modifications")
	public List<MediaModification> getAllModifications() {
		return mediaModificationService.getAllModifications();
	}

	@GetMapping("/media/modifications/administrator/{administratorId}")
	public List<MediaModification> getAdministratorModifications(@PathVariable("administratorId") Long administratorId) {
		return mediaModificationService.getAdministratorModifications(administratorId);
	}

	@GetMapping("/media/modifications/{mediaId}")
	public List<MediaModification> getMediaModifications(@PathVariable("mediaId") Long mediaId) {
		return mediaModificationService.getAdministratorModifications(mediaId);
	}

	@PostMapping("/media/newModification")
	public void addMediaModification(@RequestParam("administratorId") Long administratorId, @RequestParam("mediaId") Long mediaId,
			MediaModification modification) {

		mediaModificationService.addMediaModification(administratorId, mediaId, modification);
	}

}
