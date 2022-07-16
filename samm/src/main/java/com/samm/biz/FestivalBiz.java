package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.FestivalMapper;
import com.samm.vo.FestivalVo;

@Service
public class FestivalBiz implements Biz<Integer, FestivalVo> {

	@Autowired
	FestivalMapper festivalDao;
	
	@Override
	public void register(FestivalVo v) throws Exception {
		festivalDao.insert(v);
		
	}

	@Override
	public void modify(FestivalVo v) throws Exception {
		festivalDao.update(v);
		
	}

	@Override
	public void remove(Integer k) throws Exception {
		festivalDao.delete(k);
		
	}

	@Override
	public FestivalVo get(Integer k) throws Exception {
		return festivalDao.select(k);
	}

	@Override
	public List<FestivalVo> get() throws Exception {
		return festivalDao.selectAll();
	}
	
	public List<String> getContentId() throws Exception{
		
		return festivalDao.getContentId();
	}

}
