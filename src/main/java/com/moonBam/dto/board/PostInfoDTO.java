package com.moonBam.dto.board;

import org.apache.ibatis.type.Alias;

@Alias("PostInfoDTO")
public class PostInfoDTO {
	private long postId;
	private long viewNum;
	public PostInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostInfoDTO(long postId, long viewNum) {
		super();
		this.postId = postId;
		this.viewNum = viewNum;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getViewNum() {
		return viewNum;
	}
	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}
	@Override
	public String toString() {
		return "PostInfoDTO [postId=" + postId + ", viewNum=" + viewNum + "]";
	}

	
	
}//end class
