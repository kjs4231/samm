package com.samm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samm.biz.AdmintblBiz;
import com.samm.biz.EmailBiz;
import com.samm.biz.FestivalBiz;
import com.samm.biz.UsersBiz;
import com.samm.restapi.TourFestivalAPI;
import com.samm.vo.AdmintblVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.UsersVo;

@RestController
public class AjaxController {

	@Autowired
	AdmintblBiz adminbiz;
	@Autowired
	FestivalBiz fbiz;
	@Autowired
	TourFestivalAPI tour;
	@Autowired
	UsersBiz ubiz;
	@Autowired
	EmailBiz ebiz;

	@RequestMapping("/callArea")
	public List<FestivalVo> getAreaCode(String code) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		List<FestivalVo> list = null;
		try {
			list = fbiz.searchFestival(code, sdate, sdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/searchcontentid")
	public FestivalVo searchcontentid(Integer contentid) {
		FestivalVo vo = null;
		try {
			vo = fbiz.get(contentid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@RequestMapping("/searchmap")
	public List<FestivalVo> searchmap(String keyword, String page, String mapx, String mapy) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		System.out.println(keyword + ", " + page);
		List<FestivalVo> list = null;
		if (mapx == null || mapx.equals("")) {
			mapx = "127.052153";
			mapy = "37.5071772";
		}
		try {
			list = fbiz.searchMap(keyword, sdate, sdate, page, mapx, mapy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/countsearchmap")
	public int countsearchmap(String keyword, String page) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String sdate = today.format(date).toString();
		int count = 0;
		try {
			count = fbiz.countSearchMap(keyword, sdate, sdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@RequestMapping("checkid")
	public String checkid(String id) {
		String result = "";
		AdmintblVo vo = null;

		if (id.equals("") || id == null) {
			return "2";
		}
		if (!Pattern.matches("^[0-9a-zA-Z]*$", id)) {
			return "3";
		}
		try {
			vo = adminbiz.get(id);

			if (vo == null) {
				result = "0";
			} else {
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}// checkid

	@RequestMapping("/submitkakao")
	public void submitkakao(String kakao,String profile,HttpSession session,Model m) throws Exception {
		System.out.println(kakao);
		System.out.println(profile);
		UsersVo user = new UsersVo();
		UsersVo users = null;
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jo = (JSONObject) jsonParser.parse(kakao);
			JSONObject jo2 = (JSONObject) jsonParser.parse(profile);
			String nickname = (String) jo2.get("nickname");
			String profile_img = (String) jo2.get("profile_image_url");
			String email = (String) jo.get("email");
			String gender = (String) jo.get("gender");			
			user.setId(email);
			user.setEmail(email);
			user.setName(nickname);
			user.setProfile_img(profile_img);
			user.setGender(gender);
			try {
				users = ubiz.get(email);
				if(users.getId().equals(email)) {
					user = ubiz.get(email);
				}else {
					ubiz.kakaoLogin(user);
				}
				System.out.println("user::"+user);
				session.setAttribute("loginuser", user);
			} catch (Exception e) {
				
				e.printStackTrace();
				throw new Exception("SQL ERROR");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("user::"+user);

	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck(String id, String pwd) {
		UsersVo users = null;
		AdmintblVo admin = null;
		String result = "";
		try {
			users = ubiz.get(id);
			admin = adminbiz.get(id);
			if(users == null && admin == null) {
				result = "존재하지 않는 ID 입니다.";
			}
			if(users != null ) {
				if(users.getPwd() != pwd ) {
					result = "비밀번호가 다릅니다.";
				}
				if(users.getPwd().equals(pwd)) {
					result ="true";
				}
			}
			if(admin != null) {
				if(admin.getPwd() != pwd ) {
					result = "비밀번호가 다릅니다.";
				}
				if(admin.getPwd().equals(pwd)) {
					result ="true";
				}
			}

		} catch (Exception e) {
			return result;
		}

		
		return result;
	}

	@RequestMapping("/forgetCheck")
	public String forgetCheck(String id, String name) {
		String result = "";
		UsersVo users = null;
		
		try {
			users = ubiz.get(id);
			if(users == null) {
				result = "존재하지않는 ID 입니다.";
			}
			if(users.getName() != name ) {
				result = "ID와 이름이 일치하지 않습니다.";
			}
			if(users.getName().equals(name)) {
				ebiz.sendmail(users);
				result = "true";
			}
		} catch (Exception e) {
			return result;
		}
		
		
		
		return result;
	}
	
}
