package com.samm.votest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	UsersBiz biz;

	@Test
	void contextLoads() {
		UsersVo obj = new UsersVo("id45","p45",null);
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
