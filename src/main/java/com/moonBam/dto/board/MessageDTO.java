package com.moonBam.dto.board;

import org.apache.ibatis.type.Alias;

@Alias("MessageDTO")
public class MessageDTO {
	
	private Long messageId;
	private String senderId;
	private String receiverId;
	private String messageContent;
	private String sendDate;
	private String readStatus;
	
	public MessageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageDTO(Long messageId, String senderId, String receiverId, String messageContent, String sendDate,
			String readStatus) {
		super();
		this.messageId = messageId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageContent = messageContent;
		this.sendDate = sendDate;
		this.readStatus = readStatus;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageId=" + messageId + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", messageContent=" + messageContent + ", sendDate=" + sendDate + ", readStatus=" + readStatus + "]";
	}
	
	

}
