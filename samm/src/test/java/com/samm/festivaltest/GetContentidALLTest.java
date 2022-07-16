package com.samm.festivaltest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class GetContentidALLTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		List<String> list = null; 
		try {
			list = biz.getContentId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String obj : list) {
			System.out.println(obj);
		}
	}

}
