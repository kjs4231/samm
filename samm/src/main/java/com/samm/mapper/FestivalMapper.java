package com.samm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.FestivalVo;

@Repository
@Mapper
public interface FestivalMapper {
	public void insert(FestivalVo f) throws Exception;
	public void delete(int k) throws Exception;
	public void update(FestivalVo f) throws Exception;
	public FestivalVo select(int k) throws Exception;
	public List<FestivalVo> selectAll() throws Exception;
	public List<FestivalVo> searchFestival(HashMap<String, String> hashmap) throws Exception;
	public List<FestivalVo> searchMap(HashMap<String, String> hashmap) throws Exception;

	public void apiinsert (HashMap<String, String> hashmap) throws Exception;
	public List<String> getContentId() throws Exception;
}
