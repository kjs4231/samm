package com.samm.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class imgallowVo {

	public int iid;
	public String uid;
	public String uname;
	public String ftitle;
	public Date imgregdate;
	public String imgname;
	
	
	public imgallowVo(int iid) {
		super();
		this.iid = iid;
	}
	
	
	
}
