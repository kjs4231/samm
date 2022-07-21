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
	public String detail(Model m,Integer contentid,FestivalVo festival) {
		System.out.println("contentid::"+contentid);
		Map<String,String> common = null;
		Map<String,String> intro = null;
		Map<String,String> info = null;
		
		try {
			festival = festbiz.get(contentid); 
			intro = festbiz.getIntro(contentid);
			info = festbiz.getInfo(contentid);
			common = festbiz.getCommon(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("festival::"+festival);
		System.out.println("common::"+common);
		System.out.println("info::"+info);
		System.out.println("intro::"+intro);
		
		m.addAttribute("center","/festival/detail");
		m.addAttribute("common",common);
		m.addAttribute("info",info);
		m.addAttribute("intro",intro);
		m.addAttribute("festival",festival);	
		return "/index";
	}
	@RequestMapping("/reviewimpl")
	public String review(String contents,double star) {
		
		return "a";
	}

}
