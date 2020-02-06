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

import com.museumserver.entity.models.Exhibition;
import com.museumserver.services.ExhibitionService;

@RestController
@CrossOrigin(origins = "*")
public class ExhibitionController {

	@Autowired
	private ExhibitionService exhibitionService;

	@GetMapping("/exhibitions")
	public List<Exhibition> getExhibitions() throws IOException {
		return exhibitionService.getExhibitions();
	}

	@GetMapping("/exhibition/{id}")
	public Exhibition getExhibition(@PathVariable("id") Long id) {
		return exhibitionService.getExhibition(id);
	}

	@PostMapping("/exhibition")
	public void addExhibition(Exhibition exhibition) {
		exhibitionService.addExhibition(exhibition);
	}

	@PutMapping("/exhibition")
	public void updateExhibition(Exhibition exhibition) {
		exhibitionService.updateExhibition(exhibition);
	}

	@DeleteMapping("/exhibition")
	public void removeExhibition(@RequestParam("id") Long id) {
		exhibitionService.deleteExhibition(id);
	}

}
