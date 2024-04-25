package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("ChatgptChoicesResponseDTO")
public class ChatgptChoicesResponseDTO {
	
	private String index;
	private ChatgptMessageResponseDTO message;
	private String logprobs;
	private String finish_reason;
	public ChatgptChoicesResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatgptChoicesResponseDTO(String index, ChatgptMessageResponseDTO message, String logprobs,
			String finish_reason) {
		super();
		this.index = index;
		this.message = message;
		this.logprobs = logprobs;
		this.finish_reason = finish_reason;
	}
	@Override
	public String toString() {
		return "ChatgptChoicesResponseDTO [index=" + index + ", message=" + message + ", logprobs=" + logprobs
				+ ", finish_reason=" + finish_reason + "]";
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public ChatgptMessageResponseDTO getMessage() {
		return message;
	}
	public void setMessage(ChatgptMessageResponseDTO message) {
		this.message = message;
	}
	public String getLogprobs() {
		return logprobs;
	}
	public void setLogprobs(String logprobs) {
		this.logprobs = logprobs;
	}
	public String getFinish_reason() {
		return finish_reason;
	}
	public void setFinish_reason(String finish_reason) {
		this.finish_reason = finish_reason;
	}
	
}