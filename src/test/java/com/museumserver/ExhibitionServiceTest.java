package com.museumserver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import com.museumserver.entity.models.Exhibition;
import com.museumserver.entity.repositories.ExhibitionRepository;
import com.museumserver.services.ExhibitionServiceImpl;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExhibitionServiceTest {

	@Autowired
	private ExhibitionRepository exhibitionRepository;
	
	@Autowired
	private ExhibitionServiceImpl exhibitionService;
	
	private Exhibition originalExhibition;


	@BeforeEach
	public void setUp() {
		exhibitionService = new ExhibitionServiceImpl();
		exhibitionService.setRepository(exhibitionRepository);
		originalExhibition = exhibitionService.getExhibition(1l);
		
	}
	
	@Test
	public void updateExhibitionTest() {

		
		Date date = new Date(System.currentTimeMillis());

		Exhibition expected = new Exhibition(1l, "Pepe", date, date, "Area 51");
		
		exhibitionService.updateExhibition(expected);
		

		assertEquals( exhibitionService.getExhibition(1l).toString(), expected.toString());

	}
	@AfterEach
	public void tearDown() {

		exhibitionService.updateExhibition(originalExhibition);
		exhibitionService = null;
		
	}

}
