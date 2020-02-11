package com.museumserver.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.repositories.AdministratorRepository;
import com.museumserver.entity.repositories.ArtworkRepository;
import com.museumserver.entity.repositories.BeaconModificationRepository;
import com.museumserver.entity.repositories.BeaconRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaModificationRepository;
import com.museumserver.entity.repositories.MediaRepository;

@Controller
public class WebController {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private ArtworkRepository artworkRepository;

	@Autowired
	private BeaconRepository beaconRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Autowired
	private BeaconModificationRepository beaconModificationRepository;

	@Autowired
	private MediaModificationRepository mediaModificationRepository;
	
	@GetMapping({"/","login"})
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

		    return "redirect:index";
		}
		return "login";
	}
	
	 @RequestMapping(value = "index", method = RequestMethod.GET)
	    public ModelAndView index() {
		 
		 	HashMap<String,Long> quantities = new HashMap<String, Long>();
		 	quantities.put("obras", artworkRepository.count());
		 	quantities.put("exhibiciones", exhibitionRepository.count());
		 	quantities.put("administradores", administratorRepository.count());
		 	quantities.put("beacons", beaconRepository.count());
		 	quantities.put("media", mediaRepository.count());
		 
	        ModelAndView mav = new ModelAndView("index");
	        mav.addObject("cantidades", quantities);
	        return mav;
	    }
	 
	 @RequestMapping(value = "obras", method = RequestMethod.GET)
	    public ModelAndView artworks() {
		 
	        ModelAndView mav = new ModelAndView("artworks");
	        mav.addObject("artworks", artworkRepository.findAllByOrderByIdAsc());
	        mav.addObject("exhibitions", exhibitionRepository.findAllByOrderByIdAsc());

	        mav.addObject("newArtwork", new Artwork());
	        return mav;
	    }
}
