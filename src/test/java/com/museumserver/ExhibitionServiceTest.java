package com.museumserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import com.museumserver.entity.dao.IExhibitionDAO;
import com.museumserver.entity.models.Exhibition;
import com.museumserver.services.ExhibitionServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExhibitionServiceTest {

	@Autowired
	private IExhibitionDAO exhibitionDao;
	
	@Autowired
	private ExhibitionServiceImpl exhibitionService;
	
	private Exhibition originalExhibition;


	@BeforeEach
	public void setUp() {
		exhibitionService = new ExhibitionServiceImpl();
		exhibitionService.setDAO(exhibitionDao);
		originalExhibition = exhibitionService.getExhibition(1l);
		
	}
	
	@Test
	public void addExhibitionTest() {

		
		Date date = new Date(System.currentTimeMillis());

		Exhibition expected = new Exhibition(1l, "Pepe", date, date, "Area 51");
		
		

		assertNotNull( exhibitionService.updateExhibition(expected));

	}
	
	@AfterEach
	public void tearDown() {

		exhibitionService.updateExhibition(originalExhibition);
		exhibitionService = null;
		
	}

}
