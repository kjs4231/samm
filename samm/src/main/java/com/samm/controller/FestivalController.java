package com.samm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.FestivalBiz;
import com.samm.biz.ReviewBiz;
import com.samm.vo.FestivalVo;
import com.samm.vo.ReviewVo;

@Controller
public class FestivalController {
	
	@Autowired
	FestivalBiz festbiz;
	@Autowired
	ReviewBiz reviewbiz;
	
	@RequestMapping("/detail")
	public String detail(Model m,Integer contentid,FestivalVo festival,HttpSession session,
			ReviewVo review) {
		System.out.println("contentid::"+contentid);
		Map<String,String> common = null;
		Map<String,String> intro = null;
		Map<String,String> info = null;
		List<ReviewVo> rlist = null;
		int count = 0;
		try {
			festival = festbiz.get(contentid); 
			intro = festbiz.getIntro(contentid);
			info = festbiz.getInfo(contentid);
			common = festbiz.getCommon(contentid);
			rlist = reviewbiz.getfestivalreview(contentid);
			count = reviewbiz.getCount(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rlist::"+rlist);

		
		m.addAttribute("center","/festival/detail");
		m.addAttribute("common",common);
		m.addAttribute("rcount",count);
		m.addAttribute("review",rlist);
		m.addAttribute("info",info);
		m.addAttribute("intro",intro);
		m.addAttribute("festival",festival);	
		return "/index";
	}
	
	@RequestMapping("/reviewimpl")
	public String review(String contents,String star,String uid, int fid,HttpSession session,
			Model m,ReviewVo review) {
		System.out.println(review);
		try {
			reviewbiz.register(review);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/detail?contentid="+fid;
	}

}
