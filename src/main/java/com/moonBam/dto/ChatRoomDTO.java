package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatRoomDTO")
public class ChatRoomDTO {
	
	private int chatNum; //채팅방 번호
	private String roomTitle; //채팅방 제목
	private String roomText; //채팅방 소개글
	private int currentNow; //현재 인원수
	private int amount; //최대 인원수
	private String post;//모임장소 우편번호
	private String addr1;//모임장소 도로명주소
	private String addr2;//모임장소 지번주소
	private String cDate; //방 생성 날짜
	private String mDate; //만남 날짜
	private String category; //방 카테고리
	private String leaderId; //방장 id
	
	public ChatRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatRoomDTO(int chatNum, String roomTitle, String roomText, int currentNow, int amount, String post,
			String addr1, String addr2, String cDate, String mDate, String category, String leaderId) {
		super();
		this.chatNum = chatNum;
		this.roomTitle = roomTitle;
		this.roomText = roomText;
		this.currentNow = currentNow;
		this.amount = amount;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.cDate = cDate;
		this.mDate = mDate;
		this.category = category;
		this.leaderId = leaderId;
	}

	public int getChatNum() {
		return chatNum;
	}

	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomText() {
		return roomText;
	}

	public void setRoomText(String roomText) {
		this.roomText = roomText;
	}

	public int getCurrentNow() {
		return currentNow;
	}

	public void setCurrentNow(int currentNow) {
		this.currentNow = currentNow;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	@Override
	public String toString() {
		return "ChatRoomDTO [chatNum=" + chatNum + ", roomTitle=" + roomTitle + ", roomText=" + roomText
				+ ", currentNow=" + currentNow + ", amount=" + amount + ", post=" + post + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", cDate=" + cDate + ", mDate=" + mDate + ", category=" + category
				+ ", leaderId=" + leaderId + "]";
	}

	
}
