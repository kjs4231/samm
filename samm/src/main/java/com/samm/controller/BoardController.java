package com.samm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samm.biz.BoardBiz;
import com.samm.biz.UsersBiz;
import com.samm.vo.UsersVo;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	@Autowired
	BoardBiz bbiz;
	
	
	

}
