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
public class CommentVo {
	private Integer cno;
	private Integer bno;
	private Integer pcno;
	private String comment;
	private String commenter;
	private Date reg_date;
	private Date up_date;
	private Date del_date;
	
	private String profile_img;
	private String name;
	
	
	public CommentVo(Integer bno, Integer pcno, String comment, String commenter) {
		super();
		this.bno = bno;
		this.pcno = pcno;
		this.comment = comment;
		this.commenter = commenter;
	}


	public CommentVo(String comment) {
		super();
		this.comment = comment;
	}


	public CommentVo(Integer cno, String comment) {
		super();
		this.cno = cno;
		this.comment = comment;
	}


	public CommentVo(Integer bno, String comment, String commenter) {
		super();
		this.bno = bno;
		this.comment = comment;
		this.commenter = commenter;
	}
	
	
	
}
