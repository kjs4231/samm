package com.samm.votest.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		BoardVo obj = new BoardVo(3,"2","nusdssdsdsddsdsdsddsdsssddddddddddddddddddddddddddddddddddll","제목이 변경됨");
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
