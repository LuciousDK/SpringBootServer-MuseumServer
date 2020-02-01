package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.models.Exhibition;
import com.museumserver.entity.repositories.ExhibitionRepository;


@Service
public class ExhibitionServiceImpl implements ExhibitionService {
	
	public void setRepository(Object repository) {
		this.exhibitionRepository = (ExhibitionRepository) repository;
	}
	
	@Autowired
	private ExhibitionRepository exhibitionRepository;

	@Override
	public List<Exhibition> getExhibitions() {

		return (List<Exhibition>) exhibitionRepository.findAll();

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

			if (exhibition.getOpeningDate() != null)
				original.setOpeningDate(exhibition.getOpeningDate());

			if (exhibition.getClosingDate() != null)
				original.setClosingDate(exhibition.getClosingDate());

			if (exhibition.getLocation() != null)
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
	

}
