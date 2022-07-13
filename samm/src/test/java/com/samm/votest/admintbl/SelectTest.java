package com.samm.votest.admintbl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AdmintblBiz;
import com.samm.vo.AdmintblVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	AdmintblBiz biz;

	@Test
	void contextLoads() {
		AdmintblVo obj = null;
		try {
			obj = biz.get("admin11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
