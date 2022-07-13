package com.samm.votest.bord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;

@SpringBootTest
class ViewsTest {

	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
		try {
			biz.views(16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("조회수가 증가됨");
	}

}
