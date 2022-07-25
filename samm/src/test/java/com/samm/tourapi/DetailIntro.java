package com.samm.tourapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.restapi.TourFestivalAPI;

@SpringBootTest
class DetailIntro {


	@Autowired
	TourFestivalAPI tour;
	
	@Test
	void contextLoads() throws Exception {
		System.out.println("START----------------------------------");
		tour.DetailIntroInsert();
		System.out.println("END----------------------------------");

	}

}