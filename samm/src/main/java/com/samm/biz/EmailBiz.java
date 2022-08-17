package com.samm.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.samm.mapper.UsersMapper;
import com.samm.mapper.WishMapper;
import com.samm.vo.UsersVo;
import com.samm.vo.WishVo;

@Service
public class EmailBiz {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	UsersMapper udao;
	@Autowired
	WishMapper wishDao;

	public String randompwd() {
		Random random = new Random();
		int randompwd = random.nextInt(899999) + 100000;
		String newpwd = Integer.toString(randompwd); 
		return newpwd;
	}

	public void sendmail(UsersVo users) {
		ArrayList<String> toUserList = new ArrayList<>();
		String newpwd = randompwd();
		users.setPwd(newpwd);
		try {
			udao.update(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toUserList.add(users.getEmail());
		int toUserSize = toUserList.size();
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
		simpleMessage.setSubject("SAMM Festival 임시 비밀번호가 발급되었습니다.");
		simpleMessage.setText("임시 비밀번호는 [" + newpwd + "]입니다.");
		javaMailSender.send(simpleMessage);
	}
	
	public void sendDdayFestivalMail() {
		ArrayList<String> toUserList = new ArrayList<>();
		List<WishVo> wish = null;
		
		try {
			wish = wishDao.getAllDday();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (WishVo wishVo : wish) {
			if(wishVo.getDDay() == 7) {
				toUserList.add(wishVo.getEmail());
				int toUserSize = toUserList.size();
				SimpleMailMessage simpleMessage = new SimpleMailMessage();
				simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
				simpleMessage.setSubject("[Samm Festival]에서 찜하신 축제가 7일 남았습니다!!");
				simpleMessage.setText("Samm Festival을 이용해주셔서 감사합니다. 찜하신 축제이신"+wishVo.getTitle()+"축제가 앞으로 7일 남았습니다."
						+ "축제를 가기전 정보를 다시 확인해 보세요."+"http://49.50.174.134:8080/detail?contentid="+wishVo.getFid());
				javaMailSender.send(simpleMessage);
			}
			if(wishVo.getDDay() == 3) {
				toUserList.add(wishVo.getEmail());
				int toUserSize = toUserList.size();
				SimpleMailMessage simpleMessage = new SimpleMailMessage();
				simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
				simpleMessage.setSubject("[Samm Festival]에서 찜하신 축제가 3일 남았습니다!!");
				simpleMessage.setText("Samm Festival을 이용해주셔서 감사합니다. 찜하신 축제이신"+wishVo.getTitle()+"축제가 앞으로 3일 남았습니다."
						+ "축제를 가기전 정보를 다시 확인해 보세요."+"http://49.50.174.134:8080/detail?contentid="+wishVo.getFid());
				javaMailSender.send(simpleMessage);
			}
			if(wishVo.getDDay() == 1) {
				toUserList.add(wishVo.getEmail());
				int toUserSize = toUserList.size();
				SimpleMailMessage simpleMessage = new SimpleMailMessage();
				simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
				simpleMessage.setSubject("[Samm Festival]에서 찜하신 축제가 하루 전 입니다!!");
				simpleMessage.setText("Samm Festival을 이용해주셔서 감사합니다. 찜하신 축제이신"+wishVo.getTitle()+"축제가 앞으로 하루 전입니다!!"
						+ "까먹지 말고 축제를 가기전 정보를 다시 확인해 보세요."+"http://49.50.174.134:8080/detail?contentid="+wishVo.getFid());
				javaMailSender.send(simpleMessage);
			}
			if(wishVo.getDDay() == 0) {
				toUserList.add(wishVo.getEmail());
				int toUserSize = toUserList.size();
				SimpleMailMessage simpleMessage = new SimpleMailMessage();
				simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
				simpleMessage.setSubject("[Samm Festival]에서 찜하신 축제가 시작했습니다!!");
				simpleMessage.setText("Samm Festival을 이용해주셔서 감사합니다. 찜하신 축제이신"+wishVo.getTitle()+"축제가 시작했습니다!! 오늘을 즐기세요!"
						+ "까먹지 말고 축제를 가기전 정보를 다시 확인해 보세요."+"http://49.50.174.134:8080/detail?contentid="+wishVo.getFid());
				javaMailSender.send(simpleMessage);
			}
		}
		
		
	}

}
