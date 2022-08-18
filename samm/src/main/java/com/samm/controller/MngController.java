package com.samm.controller;

 
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.samm.biz.MngBiz;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.imgallowVo;
 
 
 
@Controller
@RequestMapping("/mng/festival")
public class MngController {
	
	@Autowired
	MngBiz mbiz; 
	
	@RequestMapping("/festivalimg")
	public String festivalimg(Model m) {
		
		List<imgallowVo> volist = null;
		try {
			volist= mbiz.iselectAllNew();
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
			mbiz.iupdateYN(iid);
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
			mbiz.iremove(iid);
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
			volist= mbiz.bget(pagingMap);
			tot = mbiz.getbCount();
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
		List<CommentVo> cvolist = null;
		
		try {
			vo = mbiz.bget(bno);
			cvolist = mbiz.getList(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("cvolist", cvolist);
		m.addAttribute("vo", vo);
		m.addAttribute("center", "mng/festival/boarddetail");
		return "mng/main";
	}
	
	
	@RequestMapping("bdelimpl")
	public String bdelimpl(Model m, int bno) {
		try {
			mbiz.bremove(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:boardlist";
	}
	
	
	@RequestMapping("cdelimpl")
	public String cdelimpl(Model m, int cno) { 
		int bno = 0;
		try {
			CommentVo cvo = mbiz.cget(cno);
			bno = cvo.getBno();
			mbiz.deleteComment(cno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:boarddetail?bno=" + bno;
	}
	
	
	@RequestMapping("bsearch")
	public String bsearch(Model m, String txt, String select) {
		
		System.out.println(txt + " / " + select);
		
		Map<String, String> map = new HashMap<String, String>(); 
		
		map.put("txt", txt);
		map.put("select", select);		
		
		List<BoardVo> volist = null;
		
		try {
			volist = mbiz.bsearch(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		m.addAttribute("volist", volist);		
		m.addAttribute("center","mng/festival/boardlist");
		return "mng/main";
	}
	
	
	@RequestMapping("/getDashboard")
	public String getDashboard(Model m) {
		
		FestivalVo fvo = null;
		List<FestivalVo> fvolist = null;
		
		LocalDate nowdate = LocalDate.now();
		
		
		try { 
			 
			fvolist = mbiz.fnowget(); 
			
			for(FestivalVo f : fvolist ) {
				 if(f.getEventenddate()==null) {
					 
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "mng/festival/boarddetail");
		return "mng/main";
	}
	
}





