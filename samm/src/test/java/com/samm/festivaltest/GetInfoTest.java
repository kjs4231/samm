package com.samm.festivaltest;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;

@SpringBootTest
class GetInfoTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		Map<String,String> map = null;
		try {
			map = biz.getInfo(2827828);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
	}

}
