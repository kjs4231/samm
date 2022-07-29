package com.samm.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.restapi.Chatbot;

@RestController
public class ChatbotController {

	
	@Autowired
	Chatbot chatbot;
	
	@RequestMapping("/chat")
	public List<String> chat(String chat) {
		System.out.println("chat::"+chat);
		List<String> result = null;
		try {
			result = chatbot.getMessage(chat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("chatbot::"+result );
		return result;
	}

}

