package com.samm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.AreaBiz;
import com.samm.biz.FestivalBiz;
import com.samm.biz.FestivalImgBiz;
import com.samm.biz.ReviewBiz;
import com.samm.frame.Util;
import com.samm.vo.FestivalImgVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.ReviewVo;


@Controller
public class FestivalController {
	
	@Autowired
	FestivalBiz festbiz;
	@Autowired
	ReviewBiz reviewbiz;
	@Autowired
	AreaBiz abiz;
	@Autowired
	FestivalImgBiz fimgbiz;
	
	@Value("${testdir}")
	String testdir;
	
	@RequestMapping("/detail")
	public String detail(Model m,Integer contentid,FestivalVo festival,HttpSession session,
			ReviewVo review) {
		System.out.println("contentid::"+contentid);
		Map<String,String> common = null;
		Map<String,String> intro = null;
		Map<String,String> info = null;
		List<ReviewVo> rlist = null;
		List<FestivalImgVo> imglist = null;
		int count = 0;
		try {
			festival = festbiz.get(contentid); 
			intro = festbiz.getIntro(contentid);
			info = festbiz.getInfo(contentid);
			common = festbiz.getCommon(contentid);
			rlist = reviewbiz.getfestivalreview(contentid);
			count = reviewbiz.getCount(contentid);
			imglist = fimgbiz.selctAllowY(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rlist::"+rlist);

		m.addAttribute("img", imglist);
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
			e.printStackTrace();
		}
		
		return "redirect:/detail?contentid="+fid;
	}
	
	@RequestMapping("/searchfestival")
	public String searchfsetival(Model m,Map<String,String> area,  String areacode, String eventstartdate, String eventenddate) {
		Date date = new Date();
		SimpleDateFormat formats = new SimpleDateFormat("yyyyMMdd");
		String today = formats.format(date).toString();
		List<Map<String,String>> alist = null;
		List<FestivalVo> list = null;
		System.out.println("eventstartdate::"+eventstartdate);
		try {
			alist = abiz.get();
			if( (areacode != null && eventstartdate != null && eventenddate != null) ) {
				list = festbiz.searchFestival(areacode, eventstartdate, eventenddate);
				int start = Integer.parseInt(eventstartdate);
				int end = Integer.parseInt(eventenddate);
				int todays = Integer.parseInt(today);			
				m.addAttribute("start", start);
				m.addAttribute("today", todays);
				m.addAttribute("end", end);
				System.out.println(start);
				System.out.println(todays);
				System.out.println(end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("festival",list);
		m.addAttribute("area",alist);
		m.addAttribute("center","/festival/searchfestival");
		return "index";
	}
	
	@RequestMapping("/searchFestivalimpl")
	public String searchFestivalimpl(Model m, String areacode, Date startdate, Date enddate) {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String eventstartdate = date.format(startdate).toString();
		String eventenddate = date.format(enddate).toString();
		
		return "redirect:/searchfestival?areacode="+areacode+"&eventstartdate="+eventstartdate+"&eventenddate="+eventenddate;
	}
	@RequestMapping("/imgimpl")
	public String imgimpl(Model m,FestivalImgVo fimg,int fid) {
		String imgname = fimg.getMf().getOriginalFilename();
		fimg.setName(imgname);
		System.out.println("fimg::"+fimg);

		
		try {
			fimgbiz.register(fimg);
			Util.saveFile(fimg.getMf(), testdir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/detail?contentid="+fid;
	}
}
