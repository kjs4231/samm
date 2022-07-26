package com.samm.votest.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		BoardVo obj = new BoardVo("title31","content3","id03");
		try {
			biz.write(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
