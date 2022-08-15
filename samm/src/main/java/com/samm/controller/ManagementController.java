package com.samm.controller;

 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.BoardBiz;
import com.samm.biz.ImgAllowBiz;
import com.samm.vo.BoardVo;
import com.samm.vo.imgallowVo;
 
 
 
@Controller
@RequestMapping("/mng/festival")
public class ManagementController {
	
	@Autowired
	ImgAllowBiz imgbiz;
	
	@Autowired
	BoardBiz boardbiz;
	
	
	
	@RequestMapping("/festivalimg")
	public String festivalimg(Model m) {
		
		List<imgallowVo> volist = null;
		try {
			volist= imgbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("volist", volist);
		m.addAttribute("center", "mng/festival/festivalimg");
		return "mng/main";
	}
	
	
	@RequestMapping("/updateYN")
	public String updateYN(Model m, imgallowVo vo) {
		System.out.println("updateYN: "+vo.toString());
		int iid = vo.getIid();
		try {
			imgbiz.updateYN(iid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:festivalimg";
	}
	
	
	@RequestMapping("fdelimpl")
	public String fdelimpl(Model m, imgallowVo vo) {
		System.out.println("fdelimpl: "+vo.toString());
		int iid = vo.getIid();
		try {
			imgbiz.remove(iid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:festivalimg";
	}
	
	
	@RequestMapping("/boardlist")
	public String boardlist(Model m, String section, String pageNum) {
		System.out.println("boardlist");
		
		if(section==null||section=="") {	
			section = "1"; 
			pageNum = "1";	
		}

		Map<String, Integer> pagingMap = new HashMap<String, Integer>(); 
		pagingMap.put("section", Integer.parseInt(section));
		pagingMap.put("pageNum", Integer.parseInt(pageNum));
		
		
		int tot = 0;		
		List<BoardVo> volist = null;
		try {
			volist= boardbiz.get(pagingMap);
			
			tot = boardbiz.getCount();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(volist==null) {
			m.addAttribute("volistnull", "게시글이 존재하지 않습니다.");			
		}else{
			m.addAttribute("volist", volist);	
		}		
		m.addAttribute("tot", tot);		
		m.addAttribute("section", section);
		m.addAttribute("pageNum", pageNum);	

		m.addAttribute("center", "mng/festival/boardlist");
		return "mng/main";
	}
	
	
	@RequestMapping("/boarddetail")
	public String boarddetail(Model m, int bno) {
		System.out.println("boarddetail: " + bno);
		
		BoardVo vo = null;
		try {
			vo = boardbiz.get(bno);
			System.out.println("boarddetail: " + vo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("vo", vo);
		m.addAttribute("center", "mng/festival/boarddetail");
		return "mng/main";
	}
	
	
	
	
	
}





