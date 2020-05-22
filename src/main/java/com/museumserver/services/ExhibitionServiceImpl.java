package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Exhibition;
import com.museumserver.entity.models.ExhibitionModification;
import com.museumserver.entity.models.Media;
import com.museumserver.entity.models.State;
import com.museumserver.entity.models.User;
import com.museumserver.entity.repositories.ExhibitionModificationRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaRepository;
import com.museumserver.entity.repositories.StateRepository;
import com.museumserver.entity.repositories.UserRepository;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Autowired
	private ExhibitionModificationRepository exhibitionModificationRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Exhibition> getAllExhibitions() {

		return (List<Exhibition>) exhibitionRepository.findAllByOrderByIdAsc();

	}

	@Override
	public List<Exhibition> getActiveExhibitions() {

		return (List<Exhibition>) exhibitionRepository.getActiveExhibitions();

	}

	@Override
	public void deleteExhibition(long id) {

		exhibitionRepository.deleteById(id);

	}

	@Override
	public void addExhibition(Exhibition exhibition) {
		exhibition.setState(stateRepository.findByName("INACTIVE"));
		exhibitionRepository.save(exhibition);
		registerAction(exhibition, "Created with name " + exhibition.getName());

	}

	@Override
	public void updateExhibition(Exhibition exhibition) {

		if (exhibitionRepository.existsById(exhibition.getId())) {

			Exhibition original = exhibitionRepository.findById(exhibition.getId()).get();
			boolean modified = false;
			String changes = "";

			if (exhibition.getName() != null)
				if (!original.getName().equalsIgnoreCase(exhibition.getName())) {
					modified = true;
					changes += "Changed name from ''" + original.getName() + "'' to ''" + exhibition.getName() + "''. ";
					original.setName(exhibition.getName());
				}

			original.setOpeningDate(exhibition.getOpeningDate());

			original.setClosingDate(exhibition.getClosingDate());

			if (exhibition.getLocation() != null) {
				if (!original.getLocation().equalsIgnoreCase(exhibition.getLocation())) {
					modified = true;
					changes += "Changed location from ''" + original.getLocation() + "'' to ''"
							+ exhibition.getLocation() + "''. ";
					original.setLocation(exhibition.getLocation());
				}
			} else {
				if (original.getLocation() != null) {
					modified = true;
					changes += "Removed from location. ";
					original.setLocation(null);
				}
			}

			exhibitionRepository.save(original);
			if (modified)
				registerAction(original, changes);
		}

	}

	@Override
	public Exhibition getExhibition(Long id) {
		if (exhibitionRepository.existsById(id))
			return exhibitionRepository.findById(id).get();
		return null;
	}

	@Override
	public void addMedia(Long exhibitionId, Long mediaId) {
		Exhibition original = exhibitionRepository.findById(exhibitionId).get();
		if (mediaRepository.existsById(mediaId)) {
			Media media = mediaRepository.findById(mediaId).get();
			original.getMedia().add(media);

			registerAction(original, "Added media ''" + media.getId() + " - " + media.getFileName() + "."
					+ media.getExtension() + "'' to media list.");

		}
		exhibitionRepository.save(original);

	}

	@Override
	public void removeMedia(Long exhibitionId, Long mediaId) {

		Exhibition original = exhibitionRepository.findById(exhibitionId).get();
		if (mediaRepository.existsById(mediaId)) {
			Media media = mediaRepository.findById(mediaId).get();
			original.getMedia().remove(media);

			registerAction(original, "Removed media ''" + media.getId() + " - " + media.getFileName() + "."
					+ media.getExtension() + "'' from media list.");

		}
		exhibitionRepository.save(original);

	}

	@Override
	public void toggleExhibition(long id) {

		Exhibition original = exhibitionRepository.findById(id).get();
		if (original.getState().getName().equalsIgnoreCase("INACTIVE")) {
			State state = stateRepository.findByName("ACTIVE");
			original.setState(state);
			exhibitionRepository.save(original);
			registerAction(original, "Set state to ''ACTIVE''");
		} else {
			State state = stateRepository.findByName("INACTIVE");
			original.setState(state);
			exhibitionRepository.save(original);
			registerAction(original, "Set state to ''INACTIVE''");
		}

	}


	private void registerAction(Exhibition exhibition, String message) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User user = userRepository.findByUsername(username);

			exhibitionModificationRepository.save(new ExhibitionModification(user, exhibition, message));

		}
	}

}
