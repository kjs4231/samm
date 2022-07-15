package com.samm.tourapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.restapi.TourFestivalAPI;

@SpringBootTest
class TourAPITEST {

	@Autowired
	TourFestivalAPI tour;

	@Test
	void contextLoads() {
		List<HashMap<String, String>> list = null;
		try {
			list = tour.searchFestival("31","20220213","20220920");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list::" + list);
		
		for (HashMap<String, String> string : list) {
			System.out.println(string.get("title"));
			System.out.println(string.get("firstimage"));
			
		}
		
		
	}	
}
