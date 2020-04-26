package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.models.ArtworkModification;
import com.museumserver.entity.models.Media;
import com.museumserver.entity.models.State;
import com.museumserver.entity.models.User;
import com.museumserver.entity.repositories.ArtworkModificationRepository;
import com.museumserver.entity.repositories.ArtworkRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaRepository;
import com.museumserver.entity.repositories.StateRepository;
import com.museumserver.entity.repositories.UserRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	@Autowired
	private ArtworkRepository artworkRepository;

	@Autowired
	private ArtworkModificationRepository artworkModificationRepository;

	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Artwork> getAllArtworks() {

		return (List<Artwork>) artworkRepository.findAllByOrderByIdAsc();

	}

	@Override
	public List<Artwork> getActiveArtworks() {

		return (List<Artwork>) artworkRepository.getActiveArtworks();

	}

	@Override
	public void deleteArtwork(long id) {

		artworkRepository.deleteById(id);

	}

	@Override
	public void addArtwork(Artwork artwork, Long exhibitionId) {

		artwork.setState(stateRepository.findByName("INACTIVE"));

		if (exhibitionId != null) {
			if (exhibitionRepository.existsById(exhibitionId)) {
				artwork.setExhibition(exhibitionRepository.findById(exhibitionId).get());
			}
		}

		artworkRepository.save(artwork);
		registerAction(artwork, "Created with name ''" + artwork.getName() + "''");

	}

	@Override
	public Artwork updateArtwork(Artwork artwork, Long exhibitionId) {
		if (artworkRepository.existsById(artwork.getId())) {
			Artwork original = artworkRepository.findById(artwork.getId()).get();
			boolean modified = false;
			String changes = "";

			if (artwork.getName() != null)
				if (!original.getName().equalsIgnoreCase(artwork.getName())) {
					modified = true;
					changes += "Changed name from ''" + original.getName() + "'' to ''" + artwork.getName() + "''. ";
					original.setName(artwork.getName());
				}

			if (artwork.getAuthor() != null)
				if (!original.getAuthor().equalsIgnoreCase(artwork.getAuthor())) {
					modified = true;
					changes += "Changed author from ''" + original.getAuthor() + "'' to ''" + artwork.getAuthor()
							+ "''. ";
					original.setAuthor(artwork.getAuthor());
				}

			if (artwork.getCountry() != null)
				if (!original.getCountry().equalsIgnoreCase(artwork.getCountry())) {
					modified = true;
					changes += "Changed country from ''" + original.getCountry() + "'' to ''" + artwork.getCountry()
							+ "''. ";
					original.setCountry(artwork.getCountry());
				}

			if (artwork.getDescription() != null)
				if (!original.getDescription().equalsIgnoreCase(artwork.getDescription())) {
					modified = true;
					changes += "Modified description. ";
					original.setDescription(artwork.getDescription());
				}

			if (exhibitionId != null) {
				if (exhibitionRepository.existsById(exhibitionId)) {
					if (original.getExhibition() != null) {
						if (!(original.getExhibition().getId() == exhibitionId)) {
							modified = true;
							changes += "Changed exhibition from ''" + original.getExhibition().getId() + " - "
									+ original.getExhibition().getName() + "'' to ''";

							original.setExhibition(exhibitionRepository.findById(exhibitionId).get());
							changes += original.getExhibition().getId() + " - " + original.getExhibition().getName()
									+ "''. ";
						}
					} else {
						modified = true;
						changes += "Assigned to exhibition ''" + exhibitionId + " - ";

						original.setExhibition(exhibitionRepository.findById(exhibitionId).get());
						changes += original.getExhibition().getId() + " - " + original.getExhibition().getName()
								+ "''. ";
					}
				}
			} else {
				if (original.getExhibition() != null) {
					modified = true;
					changes += "Removed from exhibition ''" + original.getExhibition().getId() + " - "
							+ original.getExhibition().getName() + "''. ";
					original.setExhibition(null);
				}
			}

			if (modified) {
				artworkRepository.save(original);

				registerAction(original, changes);
				return original;

			}
		}
		return null;

	}

	@Override
	public Artwork getArtwork(Long id) {
		if (artworkRepository.existsById(id))
			return artworkRepository.findById(id).get();
		return null;
	}

	@Override
	public void addMedia(Long artworkId, Long mediaId) {

		Artwork original = artworkRepository.findById(artworkId).get();
		if (mediaRepository.existsById(mediaId)) {
			Media media = mediaRepository.findById(mediaId).get();
			original.getMedia().add(media);

			registerAction(original, "Added media ''" + media.getId() + " - " + media.getFileName() + "."
					+ media.getExtension() + "'' to media list.");

		}
		artworkRepository.save(original);

	}

	@Override
	public void removeMedia(Long artworkId, Long mediaId) {

		Artwork original = artworkRepository.findById(artworkId).get();
		if (mediaRepository.existsById(mediaId)) {
			Media media = mediaRepository.findById(mediaId).get();
			original.getMedia().remove(media);

			registerAction(original, "Removed media ''" + media.getId() + " - " + media.getFileName() + "."
					+ media.getExtension() + "'' from media list.");

		}
		artworkRepository.save(original);

	}

	@Override
	public void toggleArtwork(long id) {

		Artwork original = artworkRepository.findById(id).get();
		if (original.getState().getName().equalsIgnoreCase("INACTIVE")) {
			State state = stateRepository.findByName("ACTIVE");
			original.setState(state);
			artworkRepository.save(original);
			registerAction(original, "Set state to ''ACTTIVE''");
		}else {
			State state = stateRepository.findByName("INACTIVE");
			original.setState(state);
			artworkRepository.save(original);
			registerAction(original, "Set state to ''INACTTIVE''");
		}

	}

	private void registerAction(Artwork artwork, String message) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User user = userRepository.findByUsername(username);

			artworkModificationRepository.save(new ArtworkModification(user, artwork, message));

		}
	}

}
