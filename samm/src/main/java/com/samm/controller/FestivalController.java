package com.samm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	public String detail(Model m, Integer contentid, FestivalVo festival, HttpSession session, ReviewVo review) {
		Date date = new Date();
		SimpleDateFormat formats = new SimpleDateFormat("yyyyMMdd");
		String today = formats.format(date).toString();
		Map<String, String> common = null;
		Map<String, String> intro = null;
		Map<String, String> info = null;
		List<ReviewVo> rlist = null;
		List<FestivalImgVo> imglist = null;
		int count = 0;
		try {
			festbiz.increaseViewCnt(contentid);
			festival = festbiz.selectOne(contentid);
			intro = festbiz.getIntro(contentid);
			info = festbiz.getInfo(contentid);
			common = festbiz.getCommon(contentid);
			rlist = reviewbiz.getfestivalreview(contentid);
			count = reviewbiz.getCount(contentid);
			imglist = fimgbiz.selctAllowY(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rlist::" + rlist);
		System.out.println("rlist::" + festival);

		m.addAttribute("today",today);
		m.addAttribute("img", imglist);
		m.addAttribute("center", "/festival/detail");
		m.addAttribute("common", common);
		m.addAttribute("rcount", count);
		m.addAttribute("review", rlist);
		m.addAttribute("info", info);
		m.addAttribute("intro", intro);
		m.addAttribute("festival", festival);
		return "/index";
	}

	@RequestMapping("/reviewimpl")
	public String review(String contents, String star, String uid, int fid, HttpSession session, Model m,
			ReviewVo review) {
		try {
			reviewbiz.register(review);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/detail?contentid=" + fid;
	}

	@RequestMapping("/searchfestival")
	public String searchfsetival(Model m, Map<String, String> area, String areacode, String eventstartdate,
			String eventenddate, String keyword, Integer page) throws Exception {
		String url = "";
		Date date = new Date();
		SimpleDateFormat formats = new SimpleDateFormat("yyyyMMdd");
		String today = formats.format(date).toString();

		List<Map<String, String>> areallist = null;
		List<FestivalVo> festivalList = null;
		List<FestivalVo> klist = null;
		int count = 0;
		try {
			if (keyword != null ) {
				url = URLDecoder.decode(keyword, "UTF-8");
				klist = festbiz.searchKeyword(url,page);
				count = festbiz.searchKeywordCount(keyword);
				m.addAttribute("count",count);
				m.addAttribute("keyword",keyword);
				m.addAttribute("klist", klist);
			}
			areallist = abiz.get();
			
			if ((areacode != null && eventstartdate != null && eventenddate != null)) {
				if(eventstartdate.equals("999") && eventenddate.equals("999")) {
					eventstartdate = today;
					eventenddate = today;
					festivalList = festbiz.searchFestival3(areacode, eventstartdate, eventenddate);
				}else {
					festivalList = festbiz.searchFestival2(areacode, eventstartdate, eventenddate, 2);
					int start = Integer.parseInt(eventstartdate);
					int end = Integer.parseInt(eventenddate);
					m.addAttribute("start", start);
					m.addAttribute("end", end);
				}

			}
			
			int todays = Integer.parseInt(today);
			m.addAttribute("today", todays);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("searchfsetival ERROR");
		}
		m.addAttribute("festival", festivalList);
		m.addAttribute("area", areallist);
		m.addAttribute("center", "/festival/searchfestival");
		return "index";
	}

	@RequestMapping("/searchFestivalimpl")
	public String searchFestivalimpl(Model m, String areacode, Date startdate, Date enddate) {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String eventstartdate = date.format(startdate).toString();
		String eventenddate = date.format(enddate).toString();

		return "redirect:/searchfestival?areacode=" + areacode + "&eventstartdate=" + eventstartdate + "&eventenddate="
				+ eventenddate;
	}

	@RequestMapping("/imgimpl")
	public String imgimpl(Model m, FestivalImgVo fimg, int fid) {
		String imgname = fimg.getMf().getOriginalFilename();
		fimg.setName(imgname);
		System.out.println("fimg::" + fimg);
		try {
			fimgbiz.register(fimg);
			Util.saveFile(fimg.getMf(), testdir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/detail?contentid=" + fid;
	}

	@RequestMapping("searchKeyword")
	public String searchKeyword(Model m, String keyword,Integer page) {
		String url = "";
		try {
			url = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		if (page == null) {
			page = 1;
		}

		return "redirect:/searchfestival?keyword="+url+"&page="+page;
	}
}
