package com.samm.votest.wish;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.WishBiz;
import com.samm.vo.WishVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	WishBiz biz;

	@Test
	void contextLoads() {
		List<WishVo> list = null;
		try {
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (WishVo obj : list) {
			System.out.println(obj);
		}
	}

}
