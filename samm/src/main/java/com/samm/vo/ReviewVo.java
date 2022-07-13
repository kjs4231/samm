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
    private double star;
    private Date regdate;
    private Date udate;
    private String uid;

    // 리뷰 삽입시
    public ReviewVo(String contents, double star, String uid) {
        this.contents = contents;
        this.star = star;
        this.uid = uid;
    }

    // 리뷰 수정시
    public ReviewVo(int pnum, String contents, double star) {
        this.pnum = pnum;
        this.contents = contents;
        this.star = star;
    }
}
