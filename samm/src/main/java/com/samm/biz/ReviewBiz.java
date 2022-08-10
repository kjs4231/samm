package com.samm.biz;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.ReviewMapper;
import com.samm.vo.ReviewVo;

@Service
public class ReviewBiz implements Biz<Integer, ReviewVo> {
    @Autowired
    ReviewMapper reviewDao;

    @Override
    public void register(ReviewVo v) throws Exception {
        reviewDao.insert(v);
    }

    @Override
    public void modify(ReviewVo v) throws Exception {
        reviewDao.update(v);
    }

    @Override
    public void remove(Integer k) throws Exception {
        reviewDao.delete(k);
    }

    @Override
    public ReviewVo get(Integer k) throws Exception {
        ReviewVo result = reviewDao.select(k);
        return result;
    }

    @Override
    public List<ReviewVo> get() throws Exception {
        List<ReviewVo> result = reviewDao.selectAll();
        return result;
    }

    public List<ReviewVo> list(int page) throws Exception {
        List<ReviewVo> result = reviewDao.list(page);
        return result;
    }

  

    public List<ReviewVo> getByContents(String contents, int page) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("contents", contents);
        param.put("page", page);
        List<ReviewVo> result = reviewDao.selectByContents(param);
        return result;
    }
    
    public List<ReviewVo> getfestivalreview(int fid) throws Exception{
    	return reviewDao.getfestivalreview(fid);
    }
    
	public int getCount(int contentid) throws Exception{
		return reviewDao.getCount(contentid);
	}
	
	public List<ReviewVo> selectByUid(String id) throws Exception{
		return reviewDao.selectByUid(id);
	}
}
