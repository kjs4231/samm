package com.samm.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FestivalImgVo {

	private int id;
	private String name;
	private int fid;
	private String uid;
	private Date regdate;
	
	private String title;
	private String allowYN;
	private MultipartFile mf;


	public FestivalImgVo( String name, int fid) {
		this.name = name;
		this.fid = fid;
	}


	public FestivalImgVo(String name, int fid, String uid) {
		this.name = name;
		this.fid = fid;
		this.uid = uid;
	}

	

	
	
	
}
