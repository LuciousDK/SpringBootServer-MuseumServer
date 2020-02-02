package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Artwork;
import com.museumserver.entity.repositories.ArtworkRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	public void setRepository(Object repository) {
		this.artworkRepository = (ArtworkRepository) repository;
	}

	@Autowired
	private ArtworkRepository artworkRepository;

	@Override
	public List<Artwork> getArtworks() {

		return (List<Artwork>) artworkRepository.findAll();

	}

	@Override
	public void deleteArtwork(long id) {

		artworkRepository.deleteById(id);

	}

	@Override
	public Artwork addArtwork(Artwork artwork) {

		return artworkRepository.save(artwork);

	}

	@Override
	public Artwork updateArtwork(Artwork artwork) {
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

}
