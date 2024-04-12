package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatMemberDTO")
public class ChatMemberDTO {
	
	private int chatNum; //채팅방 번호
	private String userId; //유저 id
	
	
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
	public ChatMemberDTO(int chatNum, String userId) {
		super();
		this.chatNum = chatNum;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ChatMemberDTO [chatNum=" + chatNum + ", userId=" + userId + "]";
	}

	
	
	
}
