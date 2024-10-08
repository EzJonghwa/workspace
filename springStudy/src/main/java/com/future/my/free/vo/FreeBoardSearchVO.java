package com.future.my.free.vo;

import com.future.my.common.vo.PagingVO;

public class FreeBoardSearchVO extends PagingVO{
	private String searchWord;		/*검색 키워드*/
	private String searchType;		/*검색 조건 타입 (T:제목, W:작성자, C:내용)*/
	private String searchCategory;	/*게시판 카테고리*/
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	@Override
	public String toString() {
		return "FreeBoardSearchVO [searchWord=" + searchWord + ", searchType=" + searchType + ", searchCategory="
				+ searchCategory + "]";
	}

	
}
