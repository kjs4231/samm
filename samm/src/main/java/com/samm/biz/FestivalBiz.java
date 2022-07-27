package com.samm.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samm.frame.Biz;
import com.samm.mapper.FestivalDetailMapper;
import com.samm.mapper.FestivalMapper;
import com.samm.vo.FestivalVo;

@Service
public class FestivalBiz implements Biz<Integer, FestivalVo> {

	@Autowired
	FestivalMapper festivalDao;
	@Autowired
	FestivalDetailMapper detailDao;

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

	public List<String> getContentId() throws Exception {

		return festivalDao.getContentId();
	}

	public List<FestivalVo> searchFestival(String areacode, String eventstartdate, String eventenddate, Integer page) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("areacode", areacode);
        param.put("eventstartdate", eventstartdate);
		param.put("eventenddate", eventenddate);
		if (page == null) {
			param.put("page","1");
		} else {
			param.put("page",page.toString());
		}
		return festivalDao.searchFestival(param);
	}

	public List<FestivalVo> searchFestival(String areacode, String eventstartdate, String eventenddate) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("areacode", areacode);
        param.put("eventstartdate", eventstartdate);
		param.put("eventenddate", eventenddate);
		param.put("page","1");
		return festivalDao.searchFestival(param);
	}
	
	public List<FestivalVo> searchMap(String keyword, String eventstartdate, String eventenddate, String page, String mapx, String mapy) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("eventstartdate", eventstartdate);
		param.put("eventenddate", eventenddate);
		param.put("page",page);
		param.put("mapx",mapx);
		param.put("mapy",mapy);
		System.out.println(param);
		return festivalDao.searchMap(param);
	}

	public int countSearchMap(String keyword, String eventstartdate, String eventenddate) throws Exception {
		HashMap<String, String> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("eventstartdate", eventstartdate);
		param.put("eventenddate", eventenddate);
		System.out.println(param);
		return festivalDao.countSearchMap(param);
	}

	public Map<String, String> getIntro(int contentid) throws Exception{
		
		return detailDao.getIntro(contentid); 
	}
	
	public Map<String, String> getInfo(int contentid) throws Exception{
		
		return detailDao.getInfo(contentid);	
	}
	
	public Map<String, String> getCommon(int contentid) throws Exception{
		
		return detailDao.getCommon(contentid);	
	}
	
	public List<FestivalVo> searchKeyword(String keyword, Integer page) throws Exception{
		HashMap<String, String> param = new HashMap<>();
		param.put("keyword", keyword);
		if (page == null) {
			page = 1;
		}
		page = (page-1)*12;
		param.put("page",page.toString());
		return festivalDao.searchKeyword(param);
	}
	
	public int searchKeywordCount(String keyword) throws Exception {
		int result = festivalDao.searchKeywordCount(keyword);
		return result;
	}
	

}
