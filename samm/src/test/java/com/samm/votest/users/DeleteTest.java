package com.samm.votest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.UsersBiz;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	UsersBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove("id45");
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
