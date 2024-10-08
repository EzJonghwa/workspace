package com.future.my.common.vo;

public class PagingVO {

		// 입력 받는 데이타
	private int curPage =1;				// 현재 페이지 번호
	private int rowSizePerPage =10;		// 한 페이지당 레코드(행_ ) 수
	private int pageSize =10;			// 페이지 리스트에서 보여줄 페이지 수
	private int totalRowCount;			// 총 레코드 건수
	
	// 입력받는 데이터를 통해 계산 되는
	private int firstRow;			//시작
	private int lastRow;			// 마지막
	private int totalPageCount;		// 총페이지 건수
	private int firstPage;			// 페이지 리스트에서 시작 페이지 번호
	private int lastPage;			// 페이지 리스트에서 마지막 페이지 번호
	
	
	// page 계산 (totalRowCount 를 기준으로)
	public void pageSetting() {
		// 행의 시작 및 끝 번호 계산
		firstRow =(curPage -1) * rowSizePerPage +1;
		lastRow = firstPage + rowSizePerPage -1;
		
		// 총 페이지의 수 계산
		totalPageCount = (totalRowCount -1)/ rowSizePerPage +1;
		
		// 페이지 리스트의 첫번째 및 마지막 페이지 번호 계산
		firstPage = ((curPage -1)/ pageSize) * pageSize +1;
		lastPage = firstPage + pageSize -1;
		// 마지막 페이지가 전체 페이지 수를 넘지 않더럭 조정
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}
	}
	
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getRowSizePerPage() {
		return rowSizePerPage;
	}
	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	
	
	
}
