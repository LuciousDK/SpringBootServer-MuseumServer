package com.museumserver.controllers;

import java.io.IOException;
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
import com.museumserver.entity.models.DataViews;
import com.museumserver.entity.models.Exhibition;
import com.museumserver.services.ExhibitionService;

@RestController
@CrossOrigin(origins = "*")
public class ExhibitionController {

	@Autowired
	private ExhibitionService exhibitionService;

	@GetMapping("/api/exhibitions")
	@JsonView(DataViews.ExhibitionRequest.class)
	public List<Exhibition> getExhibitions() throws IOException {
		return exhibitionService.getActiveExhibitions();
	}

	@GetMapping("/api/exhibition/{id}")
	@JsonView(DataViews.ExhibitionRequest.class)
	public Exhibition getExhibition(@PathVariable("id") Long id) {
		return exhibitionService.getExhibition(id);
	}

	@PostMapping("/api/exhibition")
	public void addExhibition(Exhibition exhibition) {
		if (exhibition.getId() == null)
			exhibitionService.addExhibition(exhibition);
		else
			exhibitionService.updateExhibition(exhibition);
	}

	@PostMapping("/api/exhibition/toggle")
	public void toggleExhibition(@RequestParam("id") Long exhibitionId) {
		exhibitionService.toggleExhibition(exhibitionId);
	}

	@PostMapping("/api/exhibition/addMedia")
	public void addMedia(@RequestParam("exhibitionId") Long exhibitionId, @RequestParam("mediaId") Long mediaId) {
		exhibitionService.addMedia(exhibitionId, mediaId);
	}

	@PostMapping("/api/exhibition/removeMedia")
	public void removeMedia(@RequestParam("exhibitionId") Long exhibitionId, @RequestParam("mediaId") Long mediaId) {
		exhibitionService.removeMedia(exhibitionId, mediaId);
	}

	@DeleteMapping("/api/exhibition")
	public void removeExhibition(@RequestParam("id") Long id) {
		exhibitionService.deleteExhibition(id);
	}

}
