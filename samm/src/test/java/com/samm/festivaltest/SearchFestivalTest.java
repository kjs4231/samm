package com.samm.festivaltest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class SearchFestivalTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		List<FestivalVo> list = null;
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String areacode = "1";
		String eventstartdate = today.format(date).toString();
		System.out.println(eventstartdate);
		String eventenddate = today.format(date).toString();
		try {
			list = biz.searchFestival(areacode, eventstartdate, eventenddate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (FestivalVo obj : list) {
			System.out.print(obj);
		}
	}

}
