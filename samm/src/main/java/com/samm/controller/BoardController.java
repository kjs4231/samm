package com.samm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.BoardBiz;
import com.samm.biz.CommentBiz;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	@Autowired
	BoardBiz bbiz;
	@Autowired
	CommentBiz cbiz;
	
	@RequestMapping("/board/write")
	public String write(Model m) {
		m.addAttribute("center", "/board/write");
		return "index";
	}
	

	@RequestMapping("/board/writeimpl")
	public String writeimpl(Model m, BoardVo u, HttpSession session, String writerid) {
		try {
			u.setWriter(writerid);
			bbiz.write(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "center");
		return "index";
	}
	
	@RequestMapping("/board/detail")
	public String detail(Model m, int bno, HttpSession session) {
		List<CommentVo> clist = null;
		
		try {
			clist = cbiz.getList(bno);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BoardVo obj = null;
		
		
			try {
				obj = bbiz.read(bno);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("dp", obj);
			m.addAttribute("clist", clist);

		m.addAttribute("center", "/board/detail");
		return "index";
	}
	
	
	
	@RequestMapping("/board/revise")
	public String revise(Model m, int bno) {
		System.out.println("revisebno "+bno);
		BoardVo board = null;
		try {
			board = bbiz.get(bno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(board);
		m.addAttribute("boardvo",board);
		m.addAttribute("center", "/board/revise");
		return "index";
	}
	

	@RequestMapping("/board/reviseimpl")
	public String reviseimpl(Model m, BoardVo u,int bno) {
		System.out.println("reviseimplbno "+bno);
		System.out.println("boardvo "+u);
		try {
			bbiz.modify(u);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center", "/board");
		return "redirect:/board/detail?bno="+bno;
	}
	
	@RequestMapping("/board/remove")
	public String remove(Model m, int bno) {
		System.out.println("removebno : "+ bno);
		
		try {
			bbiz.remove(bno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		m.addAttribute("center", "/board");
		return "redirect:/board";
	}
	@RequestMapping("/board/cwrite")
	public String cwrite(Model m, CommentVo u, int bno, HttpSession session, String commenter) {
		try {
			cbiz.write(u);
			
			u.setCommenter(commenter);
			u.setBno(bno);
			
			
			System.out.println(bno);
			System.out.println("cwrite = "+u);
			//System.out.println("writebno : "+u.getBno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "center");
		return "redirect:/board/detail?bno="+bno;
	}
	
	@RequestMapping("/board/cremove")
	public String cremove(Model m, Integer cno, Integer bno, HttpSession session) {
		System.out.println("removeccno : "+ cno);
		System.out.println("removecbno : "+ bno);
		
		try {
			cbiz.remove(cno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		m.addAttribute("center", "/board");
		return "redirect:/board/detail?bno="+bno;
	}
	
	
}


