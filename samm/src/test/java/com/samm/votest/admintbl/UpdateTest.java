package com.samm.votest.admintbl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AdmintblBiz;
import com.samm.vo.AdmintblVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	AdmintblBiz biz;

	@Test
	void contextLoads() {
		AdmintblVo obj = new AdmintblVo("admin11","p11","이름",null,null,null);
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
