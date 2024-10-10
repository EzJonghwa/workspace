package com.future.my.common.vo;

public class PagingVO {

   //입력받는 데이터
   private int curPage = 1;         // 현재페이지번호
   private int rowSizePerPage = 10;   // 한 페이지 당 레코드 (행) 수
   private int pageSize = 10;         // 페이지 리스트에서 보여 줄 페이지 수
   private int totalRowCount;         // 총 레코드 건 수
   
   //입력받는 데이터를 통해 계산되는 것
   private int firstRow;      //시작
   private int lastRow;      //마지막
   private int totalPageCount;   //총 페이지 건 수
   private int firstPage;      //페이지 리스트에서 시작 페이지 번호
   private int lastPage;      //페이지 리스트에서 마지막 페이지 번호
   
   //page 계산
   public void pageSetting() {
      //행의 시작 및 끝 번호 계산
      firstRow = (curPage - 1) * rowSizePerPage + 1;
      lastRow = firstRow + rowSizePerPage -1;
      //총 페이지 수 계싼
      totalPageCount = (totalRowCount - 1) / rowSizePerPage + 1;
      //페이지 리스트의 첫번재 및 마지막 페이지 번호 계산
      firstPage = ((curPage - 1) / pageSize) * pageSize + 1;
      lastPage = firstPage + pageSize - 1;
      //마지막 페이지가 전체 페이지 수를 넘지 않도록 조정
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
