package com.samm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.AdmintblBiz;
import com.samm.vo.AdmintblVo;
 
 
@Controller
@RequestMapping("/mng/admin")
public class AdminController {

	@Autowired
	AdmintblBiz biz;
	
	@RequestMapping("/alist")
	public String alist(Model m, String section, String pageNum) {	
		System.out.println("admin alist");
		
		if(section==null||section=="") {	
			section = "1"; 
			pageNum = "1";	
		}

		Map<String, Integer> pagingMap = new HashMap<String, Integer>(); 
		pagingMap.put("section", Integer.parseInt(section));
		pagingMap.put("pageNum", Integer.parseInt(pageNum));
		
		int tot=0;
		List<AdmintblVo> volist = null;
		
		try {
			tot = biz.getTotalNum();
			volist = biz.get(pagingMap);			
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
		m.addAttribute("center", "mng/admin/alist");		
		return "mng/main";
	}
	
	
	@RequestMapping("/ainfo")
	public String ainfo(Model m, String id) {
		AdmintblVo vo = null; 
		try {
			vo = biz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("vo", vo);		
		m.addAttribute("center", "mng/admin/ainfo");
		return "mng/main";
	}
	
	
	@RequestMapping("/aregister")
	public String aregister(Model m) {
		m.addAttribute("center", "mng/admin/aregister");
		return "mng/main";
	}
	
	
	@RequestMapping("regimpl")
	public String regimpl(Model m, AdmintblVo vo) {
		System.out.println("a-regimpl : " + vo);
		try {
			biz.register(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:ainfo?id=" + vo.getId();
	}
	
	
	@RequestMapping("/aupdate")
	public String aupdate(Model m, String id) {
		AdmintblVo vo = null; 
		try {
			vo = biz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		m.addAttribute("vo", vo);		
		m.addAttribute("center","mng/admin/aupdate");
		return "mng/main"; 
	}
		
	
	@RequestMapping("/modimpl")
	public String modimpl(Model m, AdmintblVo vo) {
		try {
			biz.modify(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:ainfo?id=" + vo.getId();
	}
	

	
  
	
	@RequestMapping("delimpl")
	public String delimpl(Model m, AdmintblVo vo) {		
		String id = vo.getId();
		try {
			biz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:alist";
	}
 
	
	
	@RequestMapping("asearch")
	public String asearch(Model m, String txt, String select) {
		
		System.out.println(txt + " / " + select);
		
		Map<String, String> map = new HashMap<String, String>(); 
		
		map.put("txt", txt);
		map.put("select", select);		
		
		List<AdmintblVo> volist = null;
		
		try {
			volist = biz.asearch(map);
			for(AdmintblVo vo : volist) {
				System.out.println(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		m.addAttribute("volist", volist);		
		m.addAttribute("center","mng/admin/alist");
		return "mng/main";
	}
	
	
	
	
}





