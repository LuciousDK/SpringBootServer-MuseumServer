package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Exhibition;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.entity.repositories.MediaRepository;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {

	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Autowired
	private MediaRepository mediaRepository;

	@Override
	public List<Exhibition> getExhibitions() {

		return (List<Exhibition>) exhibitionRepository.findAllByOrderByIdAsc();

	}

	@Override
	public void deleteExhibition(long id) {

		exhibitionRepository.deleteById(id);

	}

	@Override
	public Exhibition addExhibition(Exhibition exhibition) {

		return exhibitionRepository.save(exhibition);

	}

	@Override
	public Exhibition updateExhibition(Exhibition exhibition) {
		if (exhibitionRepository.existsById(exhibition.getId())) {
			Exhibition original = exhibitionRepository.findById(exhibition.getId()).get();
			if (exhibition.getName() != null)
				original.setName(exhibition.getName());

			original.setOpeningDate(exhibition.getOpeningDate());

			original.setClosingDate(exhibition.getClosingDate());

			original.setLocation(exhibition.getLocation());

			return exhibitionRepository.save(original);
		}
		return null;

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
			original.getMedia().add((mediaRepository.findById(mediaId).get()));
		}
		exhibitionRepository.save(original);

	}

	@Override
	public void removeMedia(Long exhibitionId, Long mediaId) {

		Exhibition original = exhibitionRepository.findById(exhibitionId).get();
		if (mediaRepository.existsById(mediaId)) {
			original.getMedia().remove(mediaRepository.findById(mediaId).get());
		}
		exhibitionRepository.save(original);

	}

}
