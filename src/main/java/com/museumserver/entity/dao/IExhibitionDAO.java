package com.museumserver.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.museumserver.entity.models.Exhibition;


public interface IExhibitionDAO extends CrudRepository<Exhibition,Long>{

}

