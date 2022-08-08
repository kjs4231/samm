package com.samm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UsersController {
	
	@Autowired
	UsersBiz ubiz;
	
	
	@RequestMapping("/users/add")
	public String add(Model m) {
		m.addAttribute("center", "/users/add");
		return "index";
	}
	

	@RequestMapping("/users/addimpl")
	public String addimpl(Model m, UsersVo u) {
		try {
			ubiz.register(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "center");
		return "index";
	}
	
	@RequestMapping("/users/forget")
	public String forget(Model m, UsersVo u) {
		
		
		m.addAttribute("center", "/users/forget");
		return "index";
	}
	
	@RequestMapping("/users/mypage")
	public String mypage(Model m) {
		
		m.addAttribute("center", "/users/mypage");
		return "index";
	}
}
