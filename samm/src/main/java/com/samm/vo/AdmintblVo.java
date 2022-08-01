package com.samm.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdmintblVo {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private String address;
    private String phone;


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
