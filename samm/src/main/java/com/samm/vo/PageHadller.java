package com.samm.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
    
    public PageHadller(int totalCnt, int page) {
    	this(totalCnt, page, 10);
    }
    
    public PageHadller(int totalCnt, int page, int pageSize) {
    	this.totalCnt = totalCnt;
    	this.page = page;
    	this.pageSize = pageSize;
    	
    	totalPage = (int) Math.ceil(totalCnt / pageSize);
    	beginPage = page / naviSize * naviSize + 1;
    	endPage = Math.min(beginPage + naviSize-1, totalPage);
    	showPrev = beginPage != 1;
    	showNext = endPage != totalPage;
    	
    }
     public void print() {
    	System.out.println("page = " + page);
    	System.out.print(showPrev ? "[PREV] " : "");
    	for(int i = beginPage; i <= endPage; i++) {
    		System.out.print(i+" ");
    	}
    	 System.out.println(showNext ? " [NEXT]" : "");
    }

	@Override
	public String toString() {
		return "PageHadller [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
				+ ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + ", showNext=" + showNext + "]";
	}
    
    

}
