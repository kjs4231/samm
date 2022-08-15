package com.samm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

@RestController
public class RegisterController {
	
	@Autowired
	UsersBiz ubiz;
	
	@RequestMapping("/idOverlapCheck")
	public boolean idOverlapCheck(String id) {
		UsersVo users = null;
		try {
			users = ubiz.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(users != null) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
}
