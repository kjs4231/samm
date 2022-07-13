package com.samm.votest.admintbl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AdmintblBiz;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	AdmintblBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove("admin11");
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
