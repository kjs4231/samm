package com.samm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.samm.biz.BoardBiz;
import com.samm.biz.CommentBiz;
import com.samm.vo.BoardVo;
import com.samm.vo.CommentVo;

import lombok.extern.log4j.Log4j2;

@RestController
public class CommentController {
	
	@Autowired
	BoardBiz bbiz;
	@Autowired
	CommentBiz cbiz;
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentVo>> list(Integer bno, Model m) {
		List<CommentVo> clist = null;
		try {
			clist = cbiz.getList(bno);
			m.addAttribute("clist", clist);
			return new ResponseEntity<List<CommentVo>>(clist, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<CommentVo>>(clist, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping("/comments/{cno}")
	 public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
		//String commenter = (String) session.getAttribute("id");
		String commenter = "id01";
				try {
					int rowCnt = cbiz.remove(cno, bno, commenter);
					
					
					if(rowCnt != 1) throw new Exception("Delete Failed");
					
					return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
				}
	}
	
	
	@PostMapping("/comments")
	 public ResponseEntity<String> write(@RequestBody CommentVo vo, Integer bno, HttpSession session){
		//String commenter = (String) session.getAttribute("id");
		String commenter = "id01";
		vo.setCommenter(commenter);
		vo.setBno(bno);
		System.out.println("vo = "+vo);
		
		try {
			if(cbiz.write(vo) != 1) throw new Exception("Write Failed");
			
			
			return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("WRT_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PatchMapping("/comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentVo vo) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "id01";
        vo.setCommenter(commenter);
        vo.setCno(cno);
        System.out.println("vo = " + vo);

        try {
            if(cbiz.modify(vo)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
	
	
}


