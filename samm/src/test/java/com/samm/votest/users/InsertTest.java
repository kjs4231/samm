package com.samm.votest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	UsersBiz biz;

	@Test
	void contextLoads() {
		UsersVo obj = new UsersVo("id45","pwd45","이름","abc@gmail.com","청담동","010-9999-9999","남성");
		try {
			biz.register(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
