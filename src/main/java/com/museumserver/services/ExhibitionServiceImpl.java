package com.museumserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.museumserver.entity.dao.IExhibitionDAO;
import com.museumserver.entity.models.Exhibition;


@Service
public class ExhibitionServiceImpl implements IExhibitionService {
	@Autowired
	private IExhibitionDAO exhibitionDao;

	@Override
	public List<Exhibition> getExhibitions() {

		return (List<Exhibition>) exhibitionDao.findAll();

	}

	@Override
	public void deleteExhibition(long id) {

		exhibitionDao.deleteById(id);

	}

	@Override
	public void addExhibition(Exhibition exhibition) {

			exhibitionDao.save(exhibition);

	}

	@Override
	public void updateExhibition(Exhibition exhibition) {

		if (exhibitionDao.existsById(exhibition.getId())) {
			
			Exhibition original = exhibitionDao.findById(exhibition.getId()).get();
			
			if (exhibition.getName() != null)
				original.setName(exhibition.getName());

			if (exhibition.getOpeningDate() != null)
				original.setOpeningDate(exhibition.getOpeningDate());

			if (exhibition.getClosingDate() != null)
				original.setClosingDate(exhibition.getClosingDate());

			if (exhibition.getLocation() != null)
				original.setLocation(exhibition.getLocation());

			exhibitionDao.save(original);
		}

	}

	@Override
	public Exhibition getExhibition(Long id) {
		if (exhibitionDao.existsById(id))
			return exhibitionDao.findById(id).get();
		return null;
	}
	

}
