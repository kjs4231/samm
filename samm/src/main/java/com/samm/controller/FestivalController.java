package com.samm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.FestivalBiz;
import com.samm.vo.FestivalVo;

@Controller
public class FestivalController {
	
	@Autowired
	FestivalBiz festbiz;
	
	@RequestMapping("/detail")
	public String detail(Model m,int contentid,FestivalVo festival) {
		Map<String,String> common = null;
		Map<String,String> info = null;
		Map<String,String> intro = null;
		try {
			festival = festbiz.get(contentid); 
			common = festbiz.getCommon(contentid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(common);
		
		m.addAttribute("dcenter","/festival/dcenter");
		m.addAttribute("common",common);
		m.addAttribute("festival",festival);	
		return "/festival/detail";
	}
	
	@RequestMapping("/common")
	public String common(Model m,int contentid,FestivalVo festival) {
		Map<String,String> common = null;
		try {
			festival = festbiz.get(contentid); 
			common = festbiz.getCommon(contentid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(common);
		
		m.addAttribute("dcenter","/festival/dcenter");
		m.addAttribute("common",common);
		m.addAttribute("festival",festival);	
		return "/festival/detail";
	}
	
	

}
