package com.samm.votest.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		List<BoardVo> list = null;
		try {
			list = biz.getList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (BoardVo obj : list) {
			System.out.println(obj);
		}
	}

}
