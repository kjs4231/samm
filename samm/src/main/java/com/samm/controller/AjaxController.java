package com.samm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.biz.AdmintblBiz;
import com.samm.biz.FestivalBiz;
import com.samm.restapi.TourFestivalAPI;
import com.samm.vo.AdmintblVo;
import com.samm.vo.FestivalVo;
 

@RestController
public class AjaxController {
	
	@Autowired
	AdmintblBiz adminbiz;
	@Autowired
	FestivalBiz fbiz;
	@Autowired
	TourFestivalAPI tour;
	
	@RequestMapping("/callArea")
	public List<FestivalVo> getAreaCode(String code) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		List<FestivalVo> list = null;
		try {
			list = fbiz.searchFestival(code, sdate, sdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("/searchmap")
	public List<FestivalVo> searchmap(String keyword, String page, String mapx, String mapy) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		System.out.println(keyword+", "+page);
		List<FestivalVo> list = null;
		if (mapx == null || mapx.equals("")){
			mapx = "127.052153";
			mapy = "37.5071772";
		}
		try {
			list = fbiz.searchMap(keyword,sdate,sdate,page,mapx,mapy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/countsearchmap")
	public int countsearchmap(String keyword, String page) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		int count = 0;
		try {
			count = fbiz.countSearchMap(keyword,sdate,sdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@RequestMapping("checkid")
	public String checkid(String id) {		
		String result = "";
		AdmintblVo vo = null;
 
		if(id.equals("") || id == null) { 
			return "2"; 
		}		
		if(!Pattern.matches("^[0-9a-zA-Z]*$",id)) { 
			return "3"; 
		}		
		try {
			vo = adminbiz.get(id);
 
			if(vo == null) {
				result = "0";
			}else{
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
 
		return result;
	}//checkid
	
	
	
	
}
