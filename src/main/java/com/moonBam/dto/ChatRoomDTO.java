package com.moonBam.dto;

import java.time.LocalDate;


import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

@Alias("ChatRoomDTO")
public class ChatRoomDTO {
	
	private int chatNum; //채팅방 번호
	private String roomTitle; //채팅방 제목
	private String roomText; //채팅방 소개글
	private int currentNow; //현재 인원수
	private int amount; //최대 인원수
	private LocalDate cDate; //방 생성 날짜
	private LocalDate mDate; //만남 날짜
	private String category; //방 카테고리
	private String leaderId; //방장 id
	private String post; //모임지역 우편번호
	private String addr1; //모임지역 주소1
	private String addr2; //모임지역 주소2
	
	
	public ChatRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
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


	public LocalDate getcDate() {
		return cDate;
	}


	public void setcDate(LocalDate cDate) {
		this.cDate = cDate;
	}


	public LocalDate getmDate() {
		return mDate;
	}


	public void setmDate(LocalDate mDate) {
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


	public ChatRoomDTO(int chatNum, String roomTitle, String roomText, int currentNow, int amount, LocalDate cDate,
			LocalDate mDate, String category, String leaderId, String post, String addr1, String addr2) {
		super();
		this.chatNum = chatNum;
		this.roomTitle = roomTitle;
		this.roomText = roomText;
		this.currentNow = currentNow;
		this.amount = amount;
		this.cDate = cDate;
		this.mDate = mDate;
		this.category = category;
		this.leaderId = leaderId;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}


	@Override
	public String toString() {
		return "ChatRoomDTO [chatNum=" + chatNum + ", roomTitle=" + roomTitle + ", roomText=" + roomText
				+ ", currentNow=" + currentNow + ", amount=" + amount + ", cDate=" + cDate + ", mDate=" + mDate
				+ ", category=" + category + ", leaderId=" + leaderId + ", post=" + post + ", addr1=" + addr1
				+ ", addr2=" + addr2 + "]";
	}
	

	
	
	
}

