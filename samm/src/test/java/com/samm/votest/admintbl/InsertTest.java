package com.samm.votest.admintbl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AdmintblBiz;
import com.samm.vo.AdmintblVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	AdmintblBiz biz;

	@Test
	void contextLoads() {
		AdmintblVo obj = new AdmintblVo("admin11","pwd11","이름","청담동","010-9999-9999",null);
		try {
			biz.register(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
