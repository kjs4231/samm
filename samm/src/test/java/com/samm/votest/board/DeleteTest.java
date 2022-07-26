package com.samm.votest.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove(2);
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
