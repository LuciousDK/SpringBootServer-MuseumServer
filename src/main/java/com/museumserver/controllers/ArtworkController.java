package com.museumserver.controllers;

import java.util.ArrayList;
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
	public List<Artwork> getArtworks(@RequestParam(required = false, value = "state") String state,
			@RequestParam(required = false, value = "name") String name) {
		if (state != null && name == null) {
			switch (state) {
			case "ACTIVE":
				return artworkService.getActiveArtworks();
			case "INACTIVE":
				return artworkService.getInactiveArtworks();
			default:
				return artworkService.getAllArtworks();
			}
		} else if (state == null && name != null && name.trim() != "") {
			List<Artwork> artworks = artworkService.getAllArtworks();
			return filterListByName(artworks, name.trim());
		} else if (state != null && name != null && name.trim() != "") {
			switch (state) {
			case "ACTIVE":
				List<Artwork> list =  artworkService.getActiveArtworks();
				return filterListByName(list, name.trim());
			case "INACTIVE":
				List<Artwork> list1 =  artworkService.getInactiveArtworks();
				return filterListByName(list1, name.trim());
			default:
				return artworkService.getAllArtworks();
			}
		}

		return artworkService.getAllArtworks();
	}

	private List<Artwork> filterListByName(List<Artwork> artworks, String name){
		ArrayList<Artwork> result = new ArrayList();
		artworks.forEach((artwork) -> {
			if (artwork.getName().contains(name.trim())) {
				result.add(artwork);
			}
		});

		return result;
	}
	
	@GetMapping("/api/artwork/{id}")
	@JsonView(DataViews.ArtworkRequest.class)
	public Artwork getArtwork(@PathVariable("id") Long id) {
		return artworkService.getArtwork(id);
	}

	@PostMapping("/api/artwork")
	public void saveArtwork(Artwork artwork,
			@RequestParam(required = false, value = "exhibitionId") Long exhibitionId) {
		if (artwork.getId() == null) {
			artworkService.addArtwork(artwork, exhibitionId);
		} else {
			artworkService.updateArtwork(artwork, exhibitionId);
		}
	}

	@PostMapping("/api/artwork/addMedia")
	public void addMedia(@RequestParam("artworkId") Long artworkId, @RequestParam("mediaId") Long mediaId) {

		artworkService.addMedia(artworkId, mediaId);
	}

	@PostMapping("/api/artwork/removeMedia")
	public void removeMedia(@RequestParam("artworkId") Long artworkId, @RequestParam("mediaId") Long mediaId) {

		artworkService.removeMedia(artworkId, mediaId);
	}

	@PostMapping("/api/artwork/delete")
	public void removeArtwork(@RequestParam("id") Long id) {
		artworkService.deleteArtwork(id);
	}

	@PostMapping("/api/artwork/toggle")
	public void toggleArtwork(@RequestParam("id") Long id) {
		artworkService.toggleArtwork(id);
	}

}
