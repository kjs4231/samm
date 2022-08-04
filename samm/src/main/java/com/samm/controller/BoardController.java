package com.samm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.BoardBiz;
import com.samm.vo.BoardVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	@Autowired
	BoardBiz bbiz;
	
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

		BoardVo obj = null;
		
			try {
				obj = bbiz.read(bno);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("dp", obj);
			

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
		return "redirect:/board";
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
	
	
}


