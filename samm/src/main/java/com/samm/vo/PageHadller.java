package com.samm.vo;

import org.springframework.web.util.UriComponentsBuilder;

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
public class PageHadller {
	private int totalCnt;
	private int pageSize;
	private int naviSize = 10;
	private int totalPage;
	private int page;
	private int beginPage;
	private int endPage;
	private boolean showPrev;
	private boolean showNext;
	private SearchCondition sc;

	public PageHadller(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;

		doPaging(totalCnt, sc);
	}

	public void doPaging(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;

		totalPage = (int) Math.ceil(totalCnt / (double) sc.getPageSize());
		beginPage = (sc.getPage() - 1) / naviSize * naviSize + 1;
		endPage = Math.min(beginPage + naviSize - 1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;

	}

	

	

	public void print() {
		System.out.println("page = " + sc.getPage());
		System.out.print(showPrev ? "[PREV] " : "");
		for (int i = beginPage; i <= endPage; i++) {
			System.out.print(i + " ");
		}
		System.out.println(showNext ? " [NEXT]" : "");
	}

}
