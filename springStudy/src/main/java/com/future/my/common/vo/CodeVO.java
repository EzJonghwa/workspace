package com.future.my.common.vo;


public class CodeVO {

	private String commCd;
	private String commNm;
	private String commParent;
	private String commOrd;
	
	//Setter, Getter
	
	public String getCommCd() {
		return commCd;
	}
	public void setCommCd(String commCd) {
		this.commCd = commCd;
	}
	public String getCommNm() {
		return commNm;
	}
	public void setCommNm(String commNm) {
		this.commNm = commNm;
	}
	public String getCommParent() {
		return commParent;
	}
	public void setCommParent(String commParent) {
		this.commParent = commParent;
	}
	public String getCommOrd() {
		return commOrd;
	}
	public void setCommOrd(String commOrd) {
		this.commOrd = commOrd;
	}
	
	@Override
	public String toString() {
		return "CodeVO [commCd=" + commCd + ", commNm=" + commNm + ", commParent=" + commParent + ", commOrd=" + commOrd
				+ "]";
	}
	//ToString 
	
}
