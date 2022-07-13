package com.samm.votest.bord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BordBiz;
import com.samm.vo.BordVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	BordBiz biz;

	@Test
	void contextLoads() {
		BordVo obj = new BordVo(16,null,"제목이 변경됨");
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
