package com.samm.votest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	UsersBiz biz;

	@Test
	void contextLoads() {
		UsersVo obj = null;
		try {
			obj = biz.get("id45");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
