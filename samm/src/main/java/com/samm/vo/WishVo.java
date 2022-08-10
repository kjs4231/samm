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
public class WishVo {
    private int id;
    private Date regdate;
    private int fid;
    private String uid;
    
    private String title;
    private String addr1;
    private String eventstartdate;
    private String eventenddate;

}
