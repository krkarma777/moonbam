package com.moonBam.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.type.Alias;

@Alias("ChatMemberDTO")
public class ChatMemberDTO {
	
	private int chatNum; //채팅방 번호
	private String userId; //유저 id
	private String enterTime; //mapper에서 sysdate 처리하세요!
	//////
	
	public int getChatNum() {
		return chatNum;
	}
	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	
	@Override
	public String toString() {
		return "ChatMemberDTO [chatNum=" + chatNum + ", userId=" + userId + ", enterTime=" + enterTime + "]";
	}
	
	public ChatMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
//		LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        this.enterTime = now.format(formatter);
	}
	public ChatMemberDTO(int chatNum, String userId, String enterTime) {
		super();
		this.chatNum = chatNum;
		this.userId = userId;
		this.enterTime = enterTime;
	}
	
	
	
	
	
	
}
