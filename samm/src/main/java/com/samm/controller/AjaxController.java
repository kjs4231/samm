package com.samm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.biz.FestivalBiz;
import com.samm.restapi.TourFestivalAPI;
import com.samm.vo.FestivalVo;

@RestController
public class AjaxController {

	@Autowired
	FestivalBiz fbiz;
	@Autowired
	TourFestivalAPI tour;
	
	@RequestMapping("/callArea")
	public List<FestivalVo> getAreaCode(String code) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		System.out.println(code);
		List<FestivalVo> list = null;
		try {
			list = fbiz.searchFestival(code, sdate, sdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	
}
