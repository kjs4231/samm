package com.samm.votest.board.comment;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samm.biz.BoardBiz;
import com.samm.biz.CommentBiz;
import com.samm.biz.UsersBiz;
import com.samm.mapper.BoardMapper;
import com.samm.mapper.CommentMapper;
import com.samm.vo.CommentVo;
import com.samm.vo.UsersVo;

@SpringBootTest
class SelectAllTest {
	
	@Autowired
	BoardBiz bbiz;
	@Autowired
	CommentBiz cbiz;

	@Test
	void contextLoads() {
		List<CommentVo> list = null;
		try {
			list = cbiz.getList(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (CommentVo obj : list) {
			System.out.println(obj);
		}
	}

}
