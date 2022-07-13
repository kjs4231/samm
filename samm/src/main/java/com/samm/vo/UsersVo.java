package com.samm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVo {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String gender;

    // 기본 생성자
    public UsersVo(String id, String pwd, String name, String email, String address, String phone, String gender) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
    
    public UsersVo() {
    }

    // 로그인
    public UsersVo(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    // 최소 회원가입
    public UsersVo(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}
