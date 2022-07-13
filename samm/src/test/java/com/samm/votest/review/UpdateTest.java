package com.samm.votest.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.ReviewBiz;
import com.samm.vo.ReviewVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	ReviewBiz biz;

	@Test
	void contextLoads() {
		ReviewVo obj = new ReviewVo(6,"밥",4);
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
