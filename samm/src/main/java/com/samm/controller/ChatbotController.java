package com.samm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatbotController {

	@RequestMapping("/chatbot")
	public String goChatBot(Model m) {
		
		return "chatbot";
	}
	
}
