package com.samm.votest.wish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.WishBiz;
import com.samm.vo.WishVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	WishBiz biz;

	@Test
	void contextLoads() {
		WishVo obj = new WishVo("festival","142","id01",null);
		try {
			biz.register(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
