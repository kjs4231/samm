package com.samm.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FestivalDetailMapper {
	

	public void introInsert (HashMap<String, String> hashmap) throws Exception;
	public void infoInsert (HashMap<String, String> hashmap) throws Exception;
	public void commonInsert (HashMap<String, String> hashmap) throws Exception;
}
