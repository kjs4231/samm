package com.samm.votest.admintbl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AdmintblBiz;
import com.samm.vo.AdmintblVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	AdmintblBiz biz;

	@Test
	void contextLoads() {
		List<AdmintblVo> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (AdmintblVo obj : list) {
			System.out.println(obj);
		}
	}

}
