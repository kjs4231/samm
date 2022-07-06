package com.samm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main(Model m) {
		
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
