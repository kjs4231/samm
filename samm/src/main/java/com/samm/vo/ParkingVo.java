package com.samm.vo;

import java.time.LocalTime;
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

public class ParkingVo {
    private String id; // id varchar(50), 주차장관리번호
    private String name; // name varchar(100), 주차장명
    private String sort; // sort varchar(5), 주차장구분
    private String type; // type varchar(5), 주차장유형
    private String address1; // address1 varchar(100), 도로명주소
    private String address2; // address2 varchar(100), 지번주소
    private int section; // section int, 주차구획수
    private String wday; // wday varchar(20), 운용요일
    private LocalTime stime; // stime time, 평일운영시작시각
    // time 개체는 이걸 쓰나...?
    private LocalTime etime; // etime time, 평일운영종료시각
    private LocalTime satstime; // satstime time, 토요일운영시작시각
    private LocalTime satetime; // satetime time, 토요일운영종료시각
    private LocalTime hstime; // hstime time, 공휴일운영시작시각
    private LocalTime hetime; // hetime time, 공휴일운영종료시각
    private String feeinfo; // feeinfo varchar(10), 요금정보
    private int parkingtime; // parkingtime int, 주차기본시간
    private int parkingfee; // parkingfee int, 주차기본요금
    private String manage; // manage varchar(50), 관리기관
    private String phone; // phone varchar(50), 전화번호
    private double latitude; // latitude double, 위도
    private double longitude; // longitude double, 경도
    private Date regdate; // regdate date; 생성일자
}
