package com.samm.biz;

import java.util.ArrayList;
import java.util.Random;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.samm.mapper.UsersMapper;
import com.samm.vo.UsersVo;

@Service
public class EmailBiz {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	UsersMapper udao;

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

}
