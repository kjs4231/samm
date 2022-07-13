package com.samm.votest.bord;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;
import com.samm.vo.BordVo;

@SpringBootTest
class ListTest {
	
	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
		List<BordVo> list = null;
		try {
			list = biz.list(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (BordVo obj : list) {
			System.out.println(obj);
		}
	}

}
