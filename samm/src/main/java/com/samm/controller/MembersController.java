package com.samm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;
 



@Controller
@RequestMapping("/mng/members")
public class MembersController {

	@Autowired
	UsersBiz biz;

	
	@RequestMapping("/mlist")
	public String mlist(Model m, String section, String pageNum) {	
		
		if(section==null||section=="") {	
			section = "1"; 
			pageNum = "1";	
		}

		Map<String, Integer> pagingMap = new HashMap<String, Integer>(); 
		pagingMap.put("section", Integer.parseInt(section));
		pagingMap.put("pageNum", Integer.parseInt(pageNum));
		
		int tot=0;
		List<UsersVo> volist = null;
		
		try {
			tot = biz.getTotalNum();
			volist = biz.selectlist(pagingMap);			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		  
		if(volist==null) {
			m.addAttribute("volistnull", "관리자 정보가 존재하지 않습니다.");				
		}else{
			m.addAttribute("volist", volist);	
		}
		
		m.addAttribute("tot", tot);
		
		m.addAttribute("section", section);
		m.addAttribute("pageNum", pageNum);		
		
		m.addAttribute("center", "mng/members/mlist");		
		return "mng/main";
	}
	

	@RequestMapping("/minfo")
	public String minfo(Model m, String id) {
		UsersVo vo = null; 
		try {
			vo = biz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("vo", vo);		
		m.addAttribute("center","members/minfo");
		return "main";
	}
	
	
	@RequestMapping("/mupdate")
	public String mupdate(Model m, String id) {

		UsersVo vo = null; 
		try {
			vo = biz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		m.addAttribute("vo", vo);		
		m.addAttribute("center","members/mupdate");
		return "main"; 
		
	}
	
	
	@RequestMapping("/modimpl")
	public String modimpl(Model m, UsersVo vo) {
		try {
			biz.modify(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:minfo?id="+vo.getId();
	}
	
	
	@RequestMapping("delimpl")
	public String delimpl(Model m, UsersVo vo) {
		
		String id = vo.getId();
		try {
			biz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:mlist";
	}
	
	
	
	@RequestMapping("msearch")
	public String msearch(Model m, String txt, String select) {
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("txt", txt);
		map.put("select", select);		
		
		List<UsersVo> volist = null;
		
		try {
			volist = biz.msearch(map);
			for(UsersVo m1 : volist) {
				System.out.println(m1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		m.addAttribute("volist", volist);		
		m.addAttribute("center","mng/members/mlist");
		return "mng/main";
	}
	
	
	
	
}

