package com.samm.festivaltest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class FestivalInsetTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		
	}

}
