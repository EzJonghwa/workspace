package com.future.my.chat.vo;

public class ChatVO {

	private int chatNo;
	private String memId;
	private String memNm;
	private int roomNo;
	private String profileImg;
	private String chatMsg;
	private String sendDate;

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public String toString() {
		return "ChatVO [chatNo=" + chatNo + ", memId=" + memId + ", memNm=" + memNm + ", roomNo=" + roomNo
				+ ", profileImg=" + profileImg + ", chatMsg=" + chatMsg + ", sendDate=" + sendDate + "]";
	}

}
