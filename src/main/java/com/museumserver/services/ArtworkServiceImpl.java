package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.models.State;
import com.museumserver.entity.repositories.ArtworkRepository;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaRepository;
import com.museumserver.entity.repositories.StateRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	@Autowired
	private ArtworkRepository artworkRepository;

	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<Artwork> getArtworks() {

		return (List<Artwork>) artworkRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteArtwork(long id) {

		artworkRepository.deleteById(id);

	}

	@Override
	public void addArtwork(Artwork artwork, Long exhibitionId) {

		if (exhibitionId != null) {
			if (exhibitionRepository.existsById(exhibitionId)) {
				artwork.setExhibition(exhibitionRepository.findById(exhibitionId).get());
			}
		}

		artworkRepository.save(artwork);

	}

	@Override
	public Artwork updateArtwork(Artwork artwork, Long exhibitionId) {
		if (artworkRepository.existsById(artwork.getId())) {
			Artwork original = artworkRepository.findById(artwork.getId()).get();

			if (artwork.getName() != null)
				original.setName(artwork.getName());

			if (artwork.getAuthor() != null)
				original.setAuthor(artwork.getAuthor());

			if (artwork.getCountry() != null)
				original.setCountry(artwork.getCountry());

			if (artwork.getDescription() != null)
				original.setDescription(artwork.getDescription());

			if (exhibitionId != null) {
				if (exhibitionRepository.existsById(exhibitionId)) {
					artwork.setExhibition(exhibitionRepository.findById(exhibitionId).get());
				}
			}

			return artworkRepository.save(original);
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
			original.getMedia().add((mediaRepository.findById(mediaId).get()));
		}
		artworkRepository.save(original);

	}

	@Override
	public void removeMedia(Long artworkId, Long mediaId) {

		Artwork original = artworkRepository.findById(artworkId).get();
		if (mediaRepository.existsById(mediaId)) {
			original.getMedia().remove(mediaRepository.findById(mediaId).get());
		}
		artworkRepository.save(original);

	}

	@Override
	public void activateArtwork(long id) {
		
		Artwork original = artworkRepository.findById(id).get();
		State state = stateRepository.findByName("ACTIVE");
		original.setState(state);
		artworkRepository.save(original);

	}

	@Override
	public void inactivateArtwork(long id) {
		
		Artwork original = artworkRepository.findById(id).get();
		State state = stateRepository.findByName("INACTIVE");
		original.setState(state);
		artworkRepository.save(original);

	}

}
