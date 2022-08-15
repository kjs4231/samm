package com.samm.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf, String testdir) {
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();

			FileOutputStream testinfo = 
					new FileOutputStream(testdir+imgname);
			testinfo.write(data);
			testinfo.close();
//			FileOutputStream adminfo = 
//					new FileOutputStream(admindir+imgname);
//			adminfo.write(data);
//			adminfo.close();

		}catch(Exception e) {
			
		}
		
	}
	
	public static void saveProfileImage(MultipartFile mf, String userdir) {
		byte [] data;
		String imgname = mf.getOriginalFilename();
		
		try {
			data = mf.getBytes();
			FileOutputStream uesrsinfo = 
					new FileOutputStream(userdir+imgname);
			uesrsinfo.write(data);
			uesrsinfo.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
}




