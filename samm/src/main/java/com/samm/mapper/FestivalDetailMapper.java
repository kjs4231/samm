package com.samm.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FestivalDetailMapper {
	

	public void introInsert (HashMap<String, String> hashmap) throws Exception;
	public void infoInsert (HashMap<String, String> hashmap) throws Exception;
	public void commonInsert (HashMap<String, String> hashmap) throws Exception;
	
	public Map<String,String> getIntro(int contentid) throws Exception;
	public Map<String,String> getInfo(int contentid) throws Exception;
	public Map<String,String> getCommon(int contentid) throws Exception;
}
