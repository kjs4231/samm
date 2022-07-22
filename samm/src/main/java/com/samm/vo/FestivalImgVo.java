package com.samm.vo;

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
	private String path;
	private int fid;
	
	
	private MultipartFile mf;


	public FestivalImgVo(int id, String name, String path, int fid) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.fid = fid;
	}


	public FestivalImgVo(int id, String name, int fid) {
		this.id = id;
		this.name = name;
		this.fid = fid;
	}
	
	
	
}
