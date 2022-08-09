package com.samm.vo;

import org.springframework.web.util.UriComponentsBuilder;

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
public class SearchCondition {
	private Integer page = 1;
	private Integer pageSize = 12;
	private Integer offset = 0;
	private String keyword = "";
	private String option = "";

	public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.option = option;
		
	}
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("pageSize", pageSize).queryParam("option", option)
				.queryParam("keyword", keyword).build().toString();
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}

	public SearchCondition(String keyword) {
		super();
		this.keyword = keyword;
	}

}
