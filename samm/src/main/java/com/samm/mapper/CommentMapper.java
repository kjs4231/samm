package com.samm.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.CommentVo;

@Repository
@Mapper
public interface CommentMapper {
	 public int count(Integer bno) throws Exception;
	 public int deleteAll(Integer bno);
	 public  int delete(Integer cno) throws Exception;
	 public int insert(CommentVo dto) throws Exception;
	 public List<CommentVo> selectAll(Integer bno) throws Exception;
	 public CommentVo select(Integer cno) throws Exception;
	 public int update(CommentVo vo) throws Exception;
	 
   
}