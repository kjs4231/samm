package com.samm.votest.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.ReviewBiz;
import com.samm.vo.ReviewVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	ReviewBiz biz;

	@Test
	void contextLoads() {
		ReviewVo obj = null;
		try {
			obj = biz.get(6);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
