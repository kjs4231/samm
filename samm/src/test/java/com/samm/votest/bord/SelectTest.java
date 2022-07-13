package com.samm.votest.bord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;
import com.samm.vo.BordVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
		BordVo obj = null;
		try {
			obj = biz.get(16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
