package com.samm.votest.bord;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

@SpringBootTest
class SelectByTitleTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		List<BoardVo> list = null;
		try {
			list = biz.getByTitle("vivendo",1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (BoardVo obj : list) {
			System.out.println(obj);
		}
	}

}
