package com.samm.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gson.JsonObject;
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
		System.out.println("boardvo::"+u);
		m.addAttribute("center", "center");
		return "redirect:/board";
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


	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		System.out.println("callback");
		JsonObject jsonObject = new JsonObject();
		String fileRoot = "C:\\multicampus finalproject\\samm\\samm\\src\\main\\resources\\static\\images\\festival\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		System.out.println("originalFileName::"+ originalFileName);
		System.out.println("extension::"+extension);
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			System.out.println("save completed");
			jsonObject.addProperty("url", "/images/festival/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			System.out.println("json");
			System.out.println(jsonObject);
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	@Configuration
	public class WebMvcConfig implements WebMvcConfigurer {
	    //web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
	    //현재 localhost:8080/summernoteImage/1234.jpg
	    //로 접속하면 C:/summernote_image/1234.jpg 파일을 불러온다.
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/images/festival/**")
	                .addResourceLocations("file:///C:/multicampus finalproject/samm/samm/src/main/resources/static/images/festival/");
	    }
	}
	
}


