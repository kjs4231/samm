package com.samm.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdmintblVo extends UsersVo {
    public AdmintblVo(String id, String pwd, String name, String address, String phone) {
        super(id, pwd, name, address, phone);
    }

    public AdmintblVo(String id, String pwd) {
        super(id, pwd);
    }

    public AdmintblVo(String id, String pwd, String name) {
        super(id, pwd, name);
    }
}