package com.moonbam.dto.board;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("ScrapDTO")
public class ScrapDTO {

	private Long scrapId;
	private String userId;
	private Long postId;
	private Date scrapDate;
	
	public ScrapDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getScrapId() {
		return scrapId;
	}

	public void setScrapId(Long scrapId) {
		this.scrapId = scrapId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Date getScrapDate() {
		return scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	@Override
	public String toString() {
		return "ScrapDTO [scrapId=" + scrapId + ", userId=" + userId + ", postId=" + postId + ", scrapDate=" + scrapDate
				+ "]";
	}

	public ScrapDTO(Long scrapId, String userId, Long postId, Date scrapDate) {
		super();
		this.scrapId = scrapId;
		this.userId = userId;
		this.postId = postId;
		this.scrapDate = scrapDate;
	}
	
	
	
	
	
	
}
