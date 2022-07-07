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
public class ReviewVo {
    private int pnum;
    private String contents;
    private String star;
    private Date regdate;
    private Date udate;
    private String uid;

    // 리뷰 수정시
    public ReviewVo(int pnum, String contents, String star) {
        this.pnum = pnum;
        this.contents = contents;
        this.star = star;
    }
}
