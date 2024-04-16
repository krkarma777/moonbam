package com.moonBam.dto.board;

import org.apache.ibatis.type.Alias;

@Alias("PostSaveDTO")
public class PostSaveDTO {

	private Long postSaveId;
	private String userId;
	private String postSaveTitle;
	private String postSaveText;
	private String postSaveDate;
	
	//기본생성자
	public PostSaveDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	//사용자 지정 생성자
	public PostSaveDTO(Long postSaveId, String userId, String postSaveTitle, String postSaveText, String postSaveDate) {
		super();
		this.postSaveId = postSaveId;
		this.userId = userId;
		this.postSaveTitle = postSaveTitle;
		this.postSaveText = postSaveText;
		this.postSaveDate = postSaveDate;
	}
	
	//get, set
	public Long getPostSaveId() {
		return postSaveId;
	}

	public void setPostSaveId(Long postSaveId) {
		this.postSaveId = postSaveId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostSaveTitle() {
		return postSaveTitle;
	}

	public void setPostSaveTitle(String postSaveTitle) {
		this.postSaveTitle = postSaveTitle;
	}

	public String getPostSaveText() {
		return postSaveText;
	}

	public void setPostSaveText(String postSaveText) {
		this.postSaveText = postSaveText;
	}

	public String getPostSaveDate() {
		return postSaveDate;
	}

	public void setPostSaveDate(String postSaveDate) {
		this.postSaveDate = postSaveDate;
		
	}

	@Override
	public String toString() {
		return "PostSaveDTO [postSaveId=" + postSaveId + ", userId=" + userId + ", postSaveTitle=" + postSaveTitle
				+ ", postSaveText=" + postSaveText + ", postSaveDate=" + postSaveDate + "]";
	}
}//end class
