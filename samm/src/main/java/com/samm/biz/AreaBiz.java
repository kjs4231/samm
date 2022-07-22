package com.samm.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.AreaMapper;

@Service
public class AreaBiz implements Biz<Integer, Map<String, String>> {

	@Autowired
	AreaMapper dao;
	
	@Override
	public void register(Map<String, String> v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Map<String, String> v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> get(Integer k) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> get() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	public List<Map<String,String>> selectArea(int code) throws Exception {
		
		return dao.selectArea(code);
	}
	
	

}
