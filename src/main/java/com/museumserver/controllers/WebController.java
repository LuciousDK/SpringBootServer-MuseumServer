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

import com.museumserver.entity.models.User;
import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.models.Exhibition;
import com.museumserver.entity.models.Media;
import com.museumserver.entity.repositories.UserRepository;
import com.museumserver.entity.repositories.ArtworkRepository;
//import com.museumserver.entity.repositories.BeaconModificationRepository;
//import com.museumserver.entity.repositories.MediaModificationRepository;
import com.museumserver.entity.repositories.BeaconRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaRepository;

@Controller
public class WebController {

	@Autowired
	private UserRepository administratorRepository;

	@Autowired
	private ArtworkRepository artworkRepository;

	@Autowired
	private BeaconRepository beaconRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private ExhibitionRepository exhibitionRepository;

//	@Autowired
//	private BeaconModificationRepository beaconModificationRepository;
//
//	@Autowired
//	private MediaModificationRepository mediaModificationRepository;

	@GetMapping({ "/", "login" })
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			return "redirect:index";
		}
		return "login";
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping(value = "artworks", method = RequestMethod.GET)
	public ModelAndView prueba() {

		ModelAndView mav = new ModelAndView("artworks");
		return mav;
	}

	@RequestMapping(value = "scan", method = RequestMethod.GET)
	public void scan() {

	}

	@RequestMapping(value = "obras", method = RequestMethod.GET)
	public ModelAndView artworks() {

		ModelAndView mav = new ModelAndView("artworks-old");
		mav.addObject("artworks", artworkRepository.findAllByOrderByIdAsc());
		mav.addObject("exhibitions", exhibitionRepository.findAllByOrderByIdAsc());
		mav.addObject("medias", mediaRepository.findAllByOrderByIdAsc());
		mav.addObject("newArtwork", new Artwork());
		return mav;
	}

	@RequestMapping(value = "archivos", method = RequestMethod.GET)
	public ModelAndView medias() {

		ModelAndView mav = new ModelAndView("medias");
		mav.addObject("medias", mediaRepository.findAllByOrderByIdAsc());
		mav.addObject("newMedia", new Media());

		return mav;
	}

	@RequestMapping(value = "exhibiciones", method = RequestMethod.GET)
	public ModelAndView exhibitions() {

		ModelAndView mav = new ModelAndView("exhibitions");
		mav.addObject("exhibitions", exhibitionRepository.findAllByOrderByIdAsc());
		mav.addObject("newExhibition", new Exhibition());
		mav.addObject("medias", mediaRepository.findAllByOrderByIdAsc());

		return mav;
	}

	@RequestMapping(value = "perfil", method = RequestMethod.GET)
	public ModelAndView profile(Authentication authentication) {

		User admin = administratorRepository.findByUsername(authentication.getName());

		ModelAndView mav = new ModelAndView("profile");

		mav.addObject("administrator", admin);

		return mav;
	}

	@RequestMapping(value = "beacons", method = RequestMethod.GET)
	public ModelAndView beacons() {

		ModelAndView mav = new ModelAndView("beacons");

		mav.addObject("beacons", beaconRepository.findAll());

		return mav;
	}

	@RequestMapping(value = "obras/informe", method = RequestMethod.GET)
	public ModelAndView artworksReport() {

		ModelAndView mav = new ModelAndView("reports/artworksReport");
		mav.addObject("artworks", artworkRepository.findAllByOrderByIdAsc());

		return mav;
	}

	@RequestMapping(value = "archivos/informe", method = RequestMethod.GET)
	public ModelAndView mediasReport() {

		ModelAndView mav = new ModelAndView("reports/mediasReport");
		mav.addObject("medias", mediaRepository.findAll());
		mav.addObject("count", mediaRepository.getCountOfType());

		return mav;
	}

	@RequestMapping(value = "exhibiciones/informe", method = RequestMethod.GET)
	public ModelAndView exhibitionsReport() {
		ModelAndView mav = new ModelAndView("reports/exhibitionsReport");
		mav.addObject("exhibitions", exhibitionRepository.findAll());

		return mav;
	}

}
