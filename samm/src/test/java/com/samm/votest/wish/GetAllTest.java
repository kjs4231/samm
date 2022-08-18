package com.samm.votest.wish;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.WishBiz;
import com.samm.vo.WishVo;

@SpringBootTest
class GetAllTest {
	
	@Autowired
	WishBiz biz;

	@Test
	void contextLoads() {
		List<WishVo> list = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		int today = Integer.parseInt(dateFormat.format(date));
		
		try {
			list = biz.getAllDday();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dDay = 0;
		for (WishVo obj : list) {
			System.out.println(obj);;
		}
	}

}
