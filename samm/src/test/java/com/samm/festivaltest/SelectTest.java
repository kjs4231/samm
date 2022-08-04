package com.samm.festivaltest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		FestivalVo obj = null;
		try {
			obj = biz.get(2825988);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
