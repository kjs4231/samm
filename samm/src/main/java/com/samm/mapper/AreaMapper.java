package com.samm.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AreaMapper {
	public void insert(HashMap<String, String> hashmap) throws Exception;
	public void insert2(HashMap<String, String> hashmap) throws Exception;
	
	public List<String> select() throws Exception; 
	public List<Map<String, String>> selectAll() throws Exception; 
}
