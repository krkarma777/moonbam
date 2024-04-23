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
	
	private int id; //행 id
	private int chatNum; //채팅방 번호 
	private String chatContent; //[ { } ] 형태로 저장될 예정 (아래 확인)
	/*[
	  {
	    "time": "2024-04-08-11-36-00",
	    "writer": "user1",
	    "text": "hello",
	    "report": "n"
	  },
	 ]
	 */
	//////
	
	@Override
	public String toString() {
		return "ChatTableDTO [id=" + id + ", chatNum=" + chatNum + ", chatContent=" + chatContent + "]";
	}


	public ChatTableDTO(MessageType type, int id, int chatNum, String chatContent) {
		super();
		this.type = type;
		this.id = id;
		this.chatNum = chatNum;
		this.chatContent = chatContent;
	}


	public ChatTableDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getChatNum() {
		return chatNum;
	}


	public void setChatNum(int chatNum) {
		this.chatNum = chatNum;
	}


	public String getChatContent() {
		return chatContent;
	}


	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}


	public MessageType getType() {
		return type;
	}


	public void setType(MessageType type) {
		this.type = type;
	}
	
	
	
	
	
	
}
