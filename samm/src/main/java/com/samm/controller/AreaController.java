package com.samm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.restapi.TourFestivalAPI;

@RestController
public class AreaController {

	@Autowired
	TourFestivalAPI tour;
	
	@RequestMapping("/callArea")
	public List<HashMap<String, String>> getAreaCode(String code) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		System.out.println(code);
		List<HashMap<String, String>> list = null;
		try {
			list = tour.searchFestival(code, sdate, sdate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
		
		return list;
	}
	
}
