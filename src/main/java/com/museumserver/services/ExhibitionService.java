package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Exhibition;


public interface ExhibitionService {

	public Exhibition getExhibition(Long id);
	
	public List<Exhibition> getAllExhibitions();
	
	public List<Exhibition> getActiveExhibitions();

	public void activateExhibition(long id);

	public void inactivateExhibition(long id);

	public void deleteExhibition(long id);

	public void addMedia(Long exhibitionId, Long mediaId);

	public void removeMedia(Long exhibitionId, Long mediaId);

	public Exhibition addExhibition(Exhibition exhibition);

	public Exhibition updateExhibition(Exhibition exhibition);
}
