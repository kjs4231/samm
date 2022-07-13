package com.samm.vo;

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
public class WishVo {
    private int id;
    private String tablename;
    private String fid;
    private String uid;
    private Integer id2;

    public WishVo(String tablename, String fid, String uid, Integer id2) {
        this.tablename = tablename;
        this.fid = fid;
        this.uid = uid;
        this.id2 = id2;
    }
}
