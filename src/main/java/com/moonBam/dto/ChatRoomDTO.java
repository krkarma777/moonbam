package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatRoomDTO")
public class ChatRoomDTO {
	
	private int chatNum; //채팅방 번호
	private String roomTitle; //채팅방 제목
	private String roomText; //채팅방 소개글
	private int count; //현재 인원수
	private int amount; //최대 인원수
	private String loc; //모임 지역
	private String cDate; //방 생성 날짜
	private String category; //방 카테고리
	private String leaderId; //방장 id
	
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	} 
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
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
		return "ChatRoomDTO [chatNum=" + chatNum + ", roomTitle=" + roomTitle + ", roomText=" + roomText + ", count="
				+ count + ", amount=" + amount + ", loc=" + loc + ", cDate=" + cDate + ", category=" + category
				+ ", leaderId=" + leaderId + "]";
	}
	public ChatRoomDTO(int chatNum, String roomTitle, String roomText, int count, int amount, String loc, String cDate,
			String category, String leaderId) {
		super();
		this.chatNum = chatNum;
		this.roomTitle = roomTitle;
		this.roomText = roomText;
		this.count = count;
		this.amount = amount;
		this.loc = loc;
		this.cDate = cDate;
		this.category = category;
		this.leaderId = leaderId;
	}
	public ChatRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
