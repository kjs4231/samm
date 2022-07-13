package com.samm.votest.bord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove(16);
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
