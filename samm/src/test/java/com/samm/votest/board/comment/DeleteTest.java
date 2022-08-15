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
class DeleteTest {

	@Autowired
	BoardBiz bbiz;
	@Autowired
	CommentBiz cbiz;

	@Test
	void contextLoads() {
		try {
//			cbiz.remove(4, 2, "asdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("delete test 완료");
	}
}
