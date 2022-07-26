package com.samm.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.BoardVo;

@Repository
@Mapper
public interface BoardMapper {
    public void insert(BoardVo v) throws Exception;
    public int delete(int k) throws Exception;
    public void update(BoardVo v) throws Exception;
    public BoardVo select(int k) throws Exception;
    public List<BoardVo> selectAll() throws Exception;

    

    public int increaseViewCnt(int k) throws Exception;
    public List<BoardVo> selectPage(Map map) throws Exception;
    public int deleteAll() throws Exception;
    public int count() throws Exception;
	/*
	 * public int searchResultCnt(SearchCondition sc) throws Exception; public
	 * List<BoardVo> searchSelectPage(SearchCondition sc) throws Exception;
	 */
}
