package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatTableDTO")
public class ChatTableDTO {
	
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
	
	@Override
	public String toString() {
		return "ChatTableDTO [id=" + id + ", chatNum=" + chatNum + ", chatContent=" + chatContent + "]";
	} 


	public ChatTableDTO(int id, int chatNum, String chatContent) {
		super();
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
	
	
	
	
	
	
}
