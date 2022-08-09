package com.samm.votest.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;
import com.samm.vo.SearchCondition;

@SpringBootTest
class SearchSeletcTest {
	
	@Autowired
	BoardBiz biz;

	@Test
	void contextLoads() {
		SearchCondition sc = new SearchCondition(1, 10, "title15", "T");
		List<BoardVo> list = null;
		
		try {
			list= biz.getSearchResultPage(sc);
			int cnt = biz.getSearchResultCnt(sc);
					System.out.println("List = " + list);
					System.out.println("cnt = " + cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
