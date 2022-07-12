package com.samm.festivaltest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class DeleteTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
	
		try {
			biz.remove(772);
			System.out.println("delete test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
