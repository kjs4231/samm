package com.samm.votest.board.comment;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.biz.CommentBiz;
import com.samm.mapper.BoardMapper;
import com.samm.mapper.CommentMapper;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;
import com.samm.vo.SearchCondition;
import com.samm.vo.UsersVo;

@SpringBootTest
class InsertTest {

	@Autowired
	BoardBiz bbiz;
	@Autowired
	CommentBiz cbiz;

	@Test
	void contextLoads() {
		CommentVo obj = new CommentVo(3, 0, "adssddsd", "id01");
		try {
			cbiz.write(obj);
			System.out.println("test 완료");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
