package com.samm.votest.bord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;
import com.samm.vo.BordVo;

@SpringBootTest
class InsertTest {
	
	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
		BordVo obj = new BordVo("내용","제목","id01");
		try {
			biz.register(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
