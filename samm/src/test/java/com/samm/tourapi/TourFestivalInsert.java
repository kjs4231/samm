package com.samm.tourapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.restapi.TourFestivalAPI;

@SpringBootTest
class TourFestivalInsert {

	@Autowired
	TourFestivalAPI tour;

	@Test
	void contextLoads()  {
			
		try {
			if(tour.insertFestivalApi()) {
				System.out.println("test");
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			

}
