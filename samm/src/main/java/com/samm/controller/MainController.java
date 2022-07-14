package com.samm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.AreaBiz;
import com.samm.restapi.TourFestivalAPI;

@Controller
public class MainController {
	
	@Autowired
	TourFestivalAPI tour;
	@Autowired
	AreaBiz abiz;
	
	
	
	@RequestMapping("/")
	public String main(Model m,String areacode,String eventstartdate, String eventenddate ) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		if(areacode == null || areacode.equals("")) {
			areacode = "1";
		}
		eventstartdate = today.format(date).toString();
		eventenddate = today.format(date).toString();
		List<HashMap<String, String>> list = null;
		List<Map<String, String>> area = null;
		try {
			list = tour.searchFestival(areacode, eventstartdate, eventenddate);
			area = abiz.get();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		m.addAttribute("area",area);
		m.addAttribute("festival",list);
		m.addAttribute("center","center");
		return "index";
	}
	
	@RequestMapping("/about")
	public String about(Model m) {
		
		m.addAttribute("center","test");
		return "index";
	}
	
	@RequestMapping("/searchfestival")
	public String searchfestival(Model m) {
		m.addAttribute("center","searchfestival");
		return "index";
	}
}
