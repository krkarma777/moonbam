package com.moonbam.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("commentDTO")
public class CommentDTO {
	
	private int comId;
	private Long postId;
	private String userId;
	private String comDate;
	private String comText;
	private String nickname;
	private int aboveCom;
	private String aboveComId;
	
	

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}



	public CommentDTO(int comId, Long postId, String userId, String comDate, String comText, String nickname,
			int aboveCom, String aboveComId) {
		super();
		this.comId = comId;
		this.postId = postId;
		this.userId = userId;
		this.comDate = comDate;
		this.comText = comText;
		this.nickname = nickname;
		this.aboveCom = aboveCom;
		this.aboveComId = aboveComId;
	}



	public int getComId() {
		return comId;
	}



	public void setComId(int comId) {
		this.comId = comId;
	}



	public Long getPostId() {
		return postId;
	}



	public void setPostId(Long postId) {
		this.postId = postId;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getComDate() {
		return comDate;
	}



	public void setComDate(String comDate) {
		this.comDate = comDate;
	}



	public String getComText() {
		return comText;
	}



	public void setComText(String comText) {
		this.comText = comText;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getAboveCom() {
		return aboveCom;
	}



	public void setAboveCom(int aboveCom) {
		this.aboveCom = aboveCom;
	}



	public String getAboveComId() {
		return aboveComId;
	}



	public void setAboveComId(String aboveComId) {
		this.aboveComId = aboveComId;
	}



	@Override
	public String toString() {
		return "CommentDTO [comId=" + comId + ", postId=" + postId + ", userId=" + userId + ", comDate=" + comDate
				+ ", comText=" + comText + ", nickname=" + nickname + ", aboveCom=" + aboveCom + ", aboveComId="
				+ aboveComId + "]";
	}



	


	
}
