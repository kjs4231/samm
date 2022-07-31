package com.samm.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersVo {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String gender;
    private String profile_img;

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

    // 카카오 로그인.
	public UsersVo(String id, String name, String email, String gender, String profile_img) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.profile_img = profile_img;
	}
    
    
}
