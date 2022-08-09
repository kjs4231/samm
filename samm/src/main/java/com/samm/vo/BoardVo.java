package com.samm.vo;

import java.util.Date;
import java.util.Objects;

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
public class BoardVo {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private int view_cnt;
	private int comment_cnt;
	private Date reg_date;
	private Date up_date;
	private Date del_date;
	private String img;
	private Integer page = 1;
	private Integer pageSize = 12;
	private Integer offset = 0;
	private String keyword = "";
	private String option = "";
	
	



	
	  @Override public int hashCode() { return Objects.hash(bno, comment_cnt,
	  content, title); }
	  
	  
	  @Override public boolean equals(Object obj) { if (this == obj) return true;
	  if (obj == null) return false; if (getClass() != obj.getClass()) return
	  false; BoardVo other = (BoardVo) obj; return Objects.equals(bno, other.bno)
	  && comment_cnt == other.comment_cnt && Objects.equals(content, other.content)
	  && Objects.equals(title, other.title); }
	 

	public BoardVo(Integer bno, String title, String content, String writer) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public BoardVo(String title, String content, String writer, String img) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.img = img;
	}

	public BoardVo(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public BoardVo(String title, String content, String img) {
		super();
		this.title = title;
		this.content = content;
		this.img = img;
	}
	
}
