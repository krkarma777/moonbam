package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatTableDTO")
public class ChatTableDTO {
	
	// 메시지 타입 : 입장, 채팅
    // 채팅방 입장 시 입장 메세지 전달을 위해 필요 (추구 주석 삭제 가능)
	public enum MessageType {
        ENTER, TALK
    }
	private MessageType type; // 메시지 타입
	
	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private int id; //행 id
	private String chatNum; //채팅방 번호 
	private String chatContent; //[ { } ] 형태로 저장될 예정 (아래 확인)
	private String nickName;
	/*[
	  {
	    "time": "2024-04-08-11-36-00",
	    "writer": "user1",
	    "text": "hello",
	  },
	 ]
	 */
	//////
	
	@Override
	public String toString() {
		return "ChatTableDTO [id=" + id + ", chatNum=" + chatNum + ", chatContent=" + chatContent + "]";
	}


	public ChatTableDTO(String chatNum, String chatContent) {
		super();
		this.chatNum = chatNum;
		this.chatContent = chatContent;
	}

	public ChatTableDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatTableDTO(String content) {
		this.chatContent = content;
	}


	public String getChatNum() {
		return chatNum;
	}

	public void setChatNum(String chatNum) {
		this.chatNum = chatNum;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
}
