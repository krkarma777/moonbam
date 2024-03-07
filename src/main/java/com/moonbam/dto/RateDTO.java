package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

@Alias("RateDTO")
public class RateDTO {
	String userId;
	Long contId;
	Integer score;
	public RateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RateDTO(String userId, Long contId, Integer score) {
		super();
		this.userId = userId;
		this.contId = contId;
		this.score = score;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getContId() {
		return contId;
	}
	public void setContId(Long contId) {
		this.contId = contId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "RateDTO [userId=" + userId + ", contId=" + contId + ", score=" + score + "]";
	}

}
