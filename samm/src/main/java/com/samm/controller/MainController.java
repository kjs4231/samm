package com.samm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.AreaBiz;
import com.samm.biz.FestivalBiz;
import com.samm.biz.UsersBiz;
import com.samm.vo.FestivalVo;
import com.samm.vo.UsersVo;

@Controller
public class MainController {
	
	@Autowired
	FestivalBiz fbiz;
	@Autowired
	AreaBiz abiz;
	@Autowired
	UsersBiz ubiz;
	
	
	
	@RequestMapping("/")
	public String main(Model m,String areacode,String eventstartdate, String eventenddate,HttpSession session ) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		if(areacode == null || areacode.equals("")) {
			areacode = "1";
		}
		eventstartdate = today.format(date).toString();
		eventenddate = today.format(date).toString();
		List<FestivalVo> list = null;
		List<Map<String, String>> area = null;
		try {
			list = fbiz.searchFestival(areacode, eventstartdate, eventenddate);
			area = abiz.get();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("area",area);
		m.addAttribute("festival",list);
		m.addAttribute("center","center");
		return "index";
	}
	
	@RequestMapping("/about")
	public String about(Model m) {
		
		m.addAttribute("center","test");
		return "index";
	}
	
	@RequestMapping("/map")
	public String map(Model m) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		List<FestivalVo> list = null;
		try {
			list = fbiz.searchFestival("1", sdate, sdate);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("festival",list);
		return "map";
	}
	@RequestMapping("/login")
	public String login(Model m) {
		
		m.addAttribute("center","login");
		return "index";
	}	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
	
		UsersVo u = null;
		try {;
			u = ubiz.get(id);
			if (u != null) {
				if (u.getPwd().equals(pwd)) {
					session.setAttribute("loginuser", u);
					m.addAttribute("loginuser", u);
					
				} else {
					throw new Exception();
					
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return login(m);
		}
		m.addAttribute("center","/center");
		return "redirect:/";
	}
	
	@RequestMapping("/logout") 
	public String logout(Model m, HttpSession session) {
		if(session !=null) {
			session.invalidate();

		}
		return "index";
	}
	
}
