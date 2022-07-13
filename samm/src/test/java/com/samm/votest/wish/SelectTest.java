package com.samm.votest.wish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.WishBiz;
import com.samm.vo.WishVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	WishBiz biz;

	@Test
	void contextLoads() {
		WishVo obj = null;
		try {
			obj = biz.get(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
