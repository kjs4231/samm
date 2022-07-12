package com.samm.festivaltest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@SpringBootTest
class UpdateTest {
	
	@Autowired
	FestivalBiz biz;

	@Test
	void contextLoads() {
		FestivalVo obj = new FestivalVo(770,"test수정","강남" , "2022-07-12", 
				"2022-07-29", "테스트수정축제", "멅티캠퍼스", "간승기", "군포시", "010-1234-5678"
				, "www.google.com", "관련정보", "군포시", "산본로", 223.657, 109.232, null);
		try {
			biz.modify(obj);
			System.out.println("update test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
