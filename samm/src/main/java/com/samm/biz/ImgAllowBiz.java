package com.samm.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.FestivalImgMapper;
import com.samm.vo.imgallowVo;

@Service
public class ImgAllowBiz implements Biz<Integer, imgallowVo> {

	@Autowired
	FestivalImgMapper dao;

	
	@Override
	public void register(imgallowVo v) throws Exception {
		
	}

	public void updateYN(Integer k) throws Exception {
		dao.updateYN(k);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.deleteNew(k);
	}
	
	
	@Override
	public imgallowVo get(Integer k) throws Exception {
		return dao.selectNew(k);
	}

	@Override
	public List<imgallowVo> get() throws Exception {
		return dao.selectAllNew();
	}

	@Override
	public void modify(imgallowVo v) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
 
	
 
}
