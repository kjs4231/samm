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
public class FestivalVo {
    private int id; // id int primary key auto_increment,
    private String name;    // name varchar(50),
    private String area;    // area varchar(50),
    private String sdate; // sdate date,
    private String edate; // edate date,
    private String contents;    // contents varchar(300),
    private String manage;  // manage varchar(50),
    private String host;    // host varchar(50),
    private String donate;  // donate varchar(50),
    private String phone;   // phone varchar(50),
    private String homepage;    // homepage varchar(50),
    private String relatedInfo; // relatedInfo varchar(20),
    private String address1;    // address1 varchar(50),
    private String address2;    // address2 varchar(50),
    private double latitude;    // latitude double,
    private double longitude;   // longitude double,
    private Date regdate;   // regdate date
    private Date udate;
    
    
	public FestivalVo(int id, String name, String area, String sdate, String edate, String contents, String manage,
			String host, String donate, String phone, String homepage, String relatedInfo, String address1,
			String address2, double latitude, double longitude, Date regdate) {
	
		this.id = id;
		this.name = name;
		this.area = area;
		this.sdate = sdate;
		this.edate = edate;
		this.contents = contents;
		this.manage = manage;
		this.host = host;
		this.donate = donate;
		this.phone = phone;
		this.homepage = homepage;
		this.relatedInfo = relatedInfo;
		this.address1 = address1;
		this.address2 = address2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.regdate = regdate;
	}




	
	
    
    
}
