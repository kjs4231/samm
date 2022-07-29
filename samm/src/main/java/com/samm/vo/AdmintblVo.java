package com.samm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdmintblVo {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String address;
    private String phone;

    // 기본 생성자
    public AdmintblVo(String id, String pwd, String name, String email, String address, String phone) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    
    public AdmintblVo() {
    }

    // 로그인
    public AdmintblVo(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    // 최소 회원가입
    public AdmintblVo(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}
