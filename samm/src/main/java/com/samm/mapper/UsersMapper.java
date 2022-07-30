package com.samm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.samm.vo.UsersVo;

@Repository
@Mapper
public interface UsersMapper {
    public void insert(UsersVo v) throws Exception;
    public void delete(String k) throws Exception;
    public void update(UsersVo v) throws Exception;
    public UsersVo select(String k) throws Exception;
    public List<UsersVo> selectAll() throws Exception;
    
    public String idCheck(String k) throws Exception;
	public int getTotalNum() throws Exception; 
	public List<UsersVo> selectlist(Map<String, Integer> m) throws Exception;
	public List<UsersVo> msearch(Map<String, String> m) throws Exception;
	
	public void kakaoLogin(UsersVo v) throws Exception;
	
}