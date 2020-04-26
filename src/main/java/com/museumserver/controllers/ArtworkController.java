package com.museumserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/api/artworks")
	@JsonView(DataViews.ArtworkRequest.class)
	public List<Artwork> getArtworks() {
		return artworkService.getActiveArtworks();
	}

	@GetMapping("/api/artwork/{id}")
	@JsonView(DataViews.ArtworkRequest.class)
	public Artwork getArtwork(@PathVariable("id") Long id) {
		return artworkService.getArtwork(id);
	}

	@PostMapping("/api/artwork")
	public void saveArtwork(Artwork artwork, @RequestParam(required = false, value = "exhibitionId") Long exhibitionId){
		if(artwork.getId()==null) {
			artworkService.addArtwork(artwork, exhibitionId);
		}else {
			artworkService.updateArtwork(artwork, exhibitionId);
		}
	}

	@PostMapping("/api/artwork/addMedia")
	public void addMedia(@RequestParam("artworkId") Long artworkId , @RequestParam("mediaId") Long mediaId){

		artworkService.addMedia(artworkId, mediaId);
	}

	@PostMapping("/api/artwork/removeMedia")
	public void removeMedia(@RequestParam("artworkId") Long artworkId , @RequestParam("mediaId") Long mediaId){

		artworkService.removeMedia(artworkId, mediaId);
	}

	@PostMapping("/api/artwork/delete")
	public void removeArtwork(@RequestParam("id") Long id){
		artworkService.deleteArtwork(id);
	}

	@PostMapping("/api/artwork/toggle")
	public void toggleArtwork(@RequestParam("id") Long id){
		artworkService.toggleArtwork(id);
	}

}
