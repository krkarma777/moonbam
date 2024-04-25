package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatMemberDTO")
public class ChatMemberDTO {
	
	private int chatNum; //채팅방 번호
	private String userId; //유저 id
	private String enterTime; //mapper에서 sysdate 처리하세요!
	private boolean isKicked;//신고 이력 유무
	
	public ChatMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatMemberDTO(int chatNum, String userId, String enterTime, boolean isKicked) {
		super();
		this.chatNum = chatNum;
		this.userId = userId;
		this.enterTime = enterTime;
		this.isKicked = isKicked;
	}

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

	public boolean isKicked() {
		return isKicked;
	}

	public void setKicked(boolean isKicked) {
		this.isKicked = isKicked;
	}

	@Override
	public String toString() {
		return "ChatMemberDTO [chatNum=" + chatNum + ", userId=" + userId + ", enterTime=" + enterTime + ", isKicked="
				+ isKicked + "]";
	}
	
	
}
