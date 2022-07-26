package com.samm.votest.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

@SpringBootTest
class SelectTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		BoardVo obj = null;
		try {
			obj = biz.read(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj);
	}

}
