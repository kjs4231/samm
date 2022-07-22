package com.samm.votest.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.ReviewBiz;
import com.samm.vo.ReviewVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	ReviewBiz biz;

	@Test
	void contextLoads() {
		ReviewVo obj = new ReviewVo("test",5,null,"id01",2492588);
		try {
			biz.register(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
