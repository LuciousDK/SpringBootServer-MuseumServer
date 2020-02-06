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

import com.fasterxml.jackson.annotation.JsonView;
import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.models.DataViews;
import com.museumserver.services.ArtworkService;

@RestController
@CrossOrigin(origins = "*")
public class ArtworkController {

	@Autowired
	private ArtworkService artworkService;

	@GetMapping("/artworks")
	@JsonView(DataViews.DefaultData.class)
	public List<Artwork> getArtworks() {
		return artworkService.getArtworks();
	}

	@GetMapping("/artwork/{id}")
	@JsonView(DataViews.DefaultData.class)
	public Artwork getArtwork(@PathVariable("id") Long id) {
		return artworkService.getArtwork(id);
	}

	@PostMapping("/artwork")
	public void addArtwork(Artwork artwork) {
		artworkService.addArtwork(artwork);
	}

	@PutMapping("/artwork")
	public void updateArtwork(Artwork artwork) {
		artworkService.updateArtwork(artwork);
	}

	@DeleteMapping("/artwork")
	public void removeArtwork(@RequestParam("id") Long id) {
		artworkService.deleteArtwork(id);
	}
	

}
