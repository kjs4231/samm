package com.samm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.samm.biz.BoardBiz;
import com.samm.biz.FestivalImgBiz;
import com.samm.biz.ReviewBiz;
import com.samm.biz.UsersBiz;
import com.samm.biz.WishBiz;
import com.samm.frame.Util;
import com.samm.vo.BoardVo;
import com.samm.vo.FestivalImgVo;
import com.samm.vo.ReviewVo;
import com.samm.vo.UsersVo;
import com.samm.vo.WishVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UsersController {
	
	@Autowired
	UsersBiz ubiz;
	@Autowired
	FestivalImgBiz fbiz;
	@Autowired
	ReviewBiz rbiz;
	@Autowired
	BoardBiz boardbiz;
	@Autowired
	WishBiz wbiz;
	@Value("${userdir}")
	String userdir;
	
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
		return "redirect:/";
	}
	
	@RequestMapping("/users/forget")
	public String forget(Model m, UsersVo u) {
		
		
		m.addAttribute("center", "/users/forget");
		return "index";
	}
	
	@RequestMapping("/users/mypage")
	public String mypage(Model m,String id) {
		System.out.println("id::"+id);
		
		List<FestivalImgVo> fimg = null;
		List<ReviewVo> review =null;
		List<BoardVo> board = null;
		List<WishVo> wish = null;
		UsersVo users = null;
		try {
			users = ubiz.get(id);
			board = boardbiz.selectByUid(id);
			review = rbiz.selectByUid(id);
			fimg = fbiz.selectByUid(id);
			wish = wbiz.getByUid(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.addAttribute("wish",wish);
		m.addAttribute("board",board);
		m.addAttribute("fimg",fimg);
		m.addAttribute("review",review);
		m.addAttribute("users",users);
		m.addAttribute("center", "/users/mypage");
		return "index";
	}
	@RequestMapping("/userProfile")
	public String userProfile(Model m, UsersVo users,String id) {
		String imgname = users.getMf().getOriginalFilename();
		users.setProfile_img(imgname);
		try {
			ubiz.modify(users);
			Util.saveFile(users.getMf(), userdir);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		return "redirect:/users/mypage?id=" + users.getId();
	}
}
