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
    private Date sdate; // sdate date,
    private Date edate; // edate date,
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
}
