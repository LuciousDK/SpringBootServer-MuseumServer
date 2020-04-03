package com.museumserver.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

	@GetMapping("/artworks")
	@JsonView(DataViews.ArtworkRequest.class)
	public List<Artwork> getArtworks() {
		return artworkService.getActiveArtworks();
	}

	@GetMapping("/artwork/{id}")
	@JsonView(DataViews.ArtworkRequest.class)
	public Artwork getArtwork(@PathVariable("id") Long id) {
		return artworkService.getArtwork(id);
	}

	@PostMapping("/artwork")
	public void addArtwork(Artwork artwork, @RequestParam(required = false, value = "exhibitionId") Long exhibitionId, HttpServletResponse response) throws IOException {
		if(artwork.getId()==null) {
			artworkService.addArtwork(artwork, exhibitionId);
		}else {
			artworkService.updateArtwork(artwork, exhibitionId);
		}
		
		response.sendRedirect("/obras");
	}

	@PostMapping("/artwork/addMedia")
	public void addMedia(@RequestParam("artworkId") Long artworkId , @RequestParam("mediaId") Long mediaId){

		artworkService.addMedia(artworkId, mediaId);
	}

	@PostMapping("/artwork/removeMedia")
	public void removeMedia(@RequestParam("artworkId") Long artworkId , @RequestParam("mediaId") Long mediaId){

		artworkService.removeMedia(artworkId, mediaId);
	}

	@PostMapping("/artwork/delete")
	public void removeArtwork(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
		artworkService.deleteArtwork(id);
		response.sendRedirect("/obras");
	}

}
