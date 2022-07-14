package com.samm.votest.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.ReviewBiz;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	ReviewBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove(7);
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
