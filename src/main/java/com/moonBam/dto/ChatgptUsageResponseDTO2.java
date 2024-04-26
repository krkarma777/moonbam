package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("ChatgptUsageResponseDTO2")
public class ChatgptUsageResponseDTO2 {
	
	private String prompt_tokens;
	private String completion_tokens;
	private String total_tokens;
	public ChatgptUsageResponseDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatgptUsageResponseDTO2(String prompt_tokens, String completion_tokens, String total_tokens) {
		super();
		this.prompt_tokens = prompt_tokens;
		this.completion_tokens = completion_tokens;
		this.total_tokens = total_tokens;
	}
	@Override
	public String toString() {
		return "ChatgptUsageResponseDTO2 [prompt_tokens=" + prompt_tokens + ", completion_tokens=" + completion_tokens
				+ ", total_tokens=" + total_tokens + "]";
	}
	public String getPrompt_tokens() {
		return prompt_tokens;
	}
	public void setPrompt_tokens(String prompt_tokens) {
		this.prompt_tokens = prompt_tokens;
	}
	public String getCompletion_tokens() {
		return completion_tokens;
	}
	public void setCompletion_tokens(String completion_tokens) {
		this.completion_tokens = completion_tokens;
	}
	public String getTotal_tokens() {
		return total_tokens;
	}
	public void setTotal_tokens(String total_tokens) {
		this.total_tokens = total_tokens;
	}
	
	

}