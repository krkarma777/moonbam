package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("ChatgptMessageResponseDTO")
public class ChatgptMessageResponseDTO {

	private String role;
	private String content;
	
	
	public ChatgptMessageResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatgptMessageResponseDTO(String role, String content) {
		super();
		this.role = role;
		this.content = content;
	}
	@Override
	public String toString() {
		return "ChatgpMessageResponseDTO [role=" + role + ", content=" + content + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}