 package com.samm.area;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.AreaBiz;

@SpringBootTest
class SelctAlltest {

	@Autowired
	AreaBiz biz;
	
	@Test
	void contextLoads() {
		List<Map<String, String>> list = null;
		try {
			
			list = biz.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Map<String, String> map : list) {
			System.out.println(map);
		}
	}

}
