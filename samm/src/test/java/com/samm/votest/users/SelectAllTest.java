package com.samm.votest.users;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	UsersBiz biz;

	@Test
	void contextLoads() {
		List<UsersVo> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UsersVo obj : list) {
			System.out.println(obj);
		}
	}

}
