package com.samm.chatbot;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.restapi.Chatbot;



@SpringBootTest
class ChatbotTest {

	@Autowired
	Chatbot chatbot;
	
	
	
	@Test
	void contextLoads() throws IOException {
		
		String txt = "서울 축제";
		List<String> result = null;
		result = chatbot.getMessage(txt);
		
		System.out.println(result);

	}
	
	
}
