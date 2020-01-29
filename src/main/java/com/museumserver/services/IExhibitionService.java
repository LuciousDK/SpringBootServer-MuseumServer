package com.museumserver.services;

import java.util.List;

import com.museumserver.entity.models.Exhibition;


public interface IExhibitionService {

	public Exhibition getExhibition(Long id);
	
	public List<Exhibition> getExhibitions();

	public void deleteExhibition(long id);

	public void addExhibition(Exhibition exhibition);

	public Exhibition updateExhibition(Exhibition exhibition);
}
