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
    private int contentid; // id int primary key auto_increment,
    private String title;    // name varchar(50),
    private String areacode;    // area varchar(50),
    private String eventstartdate; // sdate date,
    private String eventenddate; // edate date,
    private String tel;   // phone varchar(50),
    private String addr1;    // address1 varchar(50),
    private double mapx;    // latitude double,
    private double mapy;   // longitude double,
    private Date createdtime;   // regdate date
    private String firstimage;
    private String firstimage2;
    
}
