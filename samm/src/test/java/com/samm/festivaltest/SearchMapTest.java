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
class SearchMapTest {

	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		List<FestivalVo> list = null;
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String eventstartdate = today.format(date).toString();
		System.out.println(eventstartdate);
		String eventenddate = today.format(date).toString();
		String page = "1";
		String mapx = "126.955869";
		String mapy = "37.546037";		
		String keyword = "서울";
		int count = 0;
		try {
			list = biz.searchMap(keyword,eventstartdate, eventenddate, page, mapx, mapy);
			count = biz.countSearchMap(keyword, eventstartdate, eventenddate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (FestivalVo obj : list) {
			System.out.println(obj);
		}
		System.out.println(count);
	}

}
