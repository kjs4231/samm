package com.samm.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf, String testdir) {
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
//			FileOutputStream adminfo = 
//					new FileOutputStream(admindir+imgname);
//			adminfo.write(data);
//			adminfo.close();
			FileOutputStream testinfo = 
					new FileOutputStream(testdir+imgname);
			testinfo.write(data);
			testinfo.close();
		}catch(Exception e) {
			
		}
		
	}
	
}




