package com.samm.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.samm.biz.AdmintblBiz;
import com.samm.biz.EmailBiz;
import com.samm.biz.FestivalBiz;
import com.samm.biz.ReviewBiz;
import com.samm.biz.UsersBiz;
import com.samm.biz.WishBiz;
import com.samm.restapi.TourFestivalAPI;
import com.samm.vo.AdmintblVo;
import com.samm.vo.FestivalVo;
import com.samm.vo.ReviewVo;
import com.samm.vo.UsersVo;
import com.samm.vo.WishVo;

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
	@Autowired
	ReviewBiz rbiz;
	@Autowired
	WishBiz wbiz;

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
	public List<FestivalVo> searchmap(String keyword, String page, String mapx, String mapy, String eventstartdate, String eventenddate) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		if (eventstartdate == null) {
			eventstartdate = today.format(date).toString();
		}
		if (eventenddate == null) {
			eventenddate = today.format(date).toString();
		}
		List<FestivalVo> list = null;
		if (mapx == null || mapx.equals("")) {
			mapx = "127.052153";
			mapy = "37.5071772";
		}
		try {
			list = fbiz.searchMap(keyword, eventstartdate, eventenddate, page, mapx, mapy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping("/countsearchmap")
	public int countsearchmap(String keyword, String page,String eventstartdate, String eventenddate) {
		Date date = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		if (eventstartdate == null) {
			eventstartdate = today.format(date).toString();
		}
		if (eventenddate == null) {
			eventenddate = today.format(date).toString();
		}
		int count = 0;
		try {
			count = fbiz.countSearchMap(keyword, eventstartdate, eventenddate);
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
	
	@RequestMapping("/modifyReview")
	public String modifyReview(ReviewVo review) {
		try {
			rbiz.modify(review);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
	
	@RequestMapping("/deleteReview")
	public String deleteReview(int pnum) {
		try {
			rbiz.remove(pnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "삭제가 완료 되었습니다.";
	}
	
	@RequestMapping("/getWish")
	public String getWish(String loginuser,int fid) {
		System.out.println(loginuser);
		List<WishVo> wishList = null;
		String result = "bi-heart";
		try {
			wishList = wbiz.getByUid(loginuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("wishList::"+wishList);
		// 로그인 안했을때
		if(wishList == null || wishList.isEmpty() ) {
			result = "bi-heart";
		// 로그인 했을때 찜 했는지 안했는지 
		}else {
			for (WishVo wish : wishList) {
				if(wish.getFid() == fid) {
					result = "bi-heart-fill";
				}
			}
		}
		
		return result;
	}
	@RequestMapping("/registerWish")
	public String registerWish(WishVo wish) {
		
		try {
			wbiz.register(wish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "찜완료!";
	}
	
	@RequestMapping("/deleteWish")
	public String deleteWish(WishVo wish) {
		
		try {
			wbiz.deleteWish(wish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "찜제거!";
	}
	

	@RequestMapping("/updateMypage")
	public void updateMypage(UsersVo mypage) {
		
		System.out.println(mypage);
		try {
			ubiz.modify(mypage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
