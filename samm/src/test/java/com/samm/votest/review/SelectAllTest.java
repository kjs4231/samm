package com.samm.votest.review;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.ReviewBiz;
import com.samm.vo.ReviewVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	ReviewBiz biz;

	@Test
	void contextLoads() {
		List<ReviewVo> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReviewVo obj : list) {
			System.out.println(obj);
		}
	}

}
