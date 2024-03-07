package com.moonbam.dto;

import org.apache.ibatis.type.Alias;

// review를 불러올 때 별점, 작성자 닉네임까지 한번의 sql로 받아오기 위해 만든 DTO
@Alias("ReviewDTO")
public class ReviewDTO {
	private Long postId;
	private String postBoard;
	private String userId;
	private Long contId;
	private String postTitle;
	private String postDate;
	private String editDate;
	private String postText;
	private String nickname;  //닉네임
	private String score;  //별점
	private String likeUserId; //리뷰리스트 불러올 때 해당 사용자가 해당 리뷰에 공감 눌렀는지 저장할 변수
	private String isLike; // 공감 조회시 인자로 사용할 사용자 변수
	private String likeNum; //공감수
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewDTO(Long postId, String postBoard, String userId, Long contId, String postTitle, String postDate,
			String editDate, String postText, String nickname, String score, String likeUserId, String isLike,
			String likeNum) {
		super();
		this.postId = postId;
		this.postBoard = postBoard;
		this.userId = userId;
		this.contId = contId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.editDate = editDate;
		this.postText = postText;
		this.nickname = nickname;
		this.score = score;
		this.likeUserId = likeUserId;
		this.isLike = isLike;
		this.likeNum = likeNum;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getPostBoard() {
		return postBoard;
	}
	public void setPostBoard(String postBoard) {
		this.postBoard = postBoard;
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
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(String likeUserId) {
		this.likeUserId = likeUserId;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	public String getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}
	@Override
	public String toString() {
		return "ReviewDTO [postId=" + postId + ", postBoard=" + postBoard + ", userId=" + userId + ", contId=" + contId
				+ ", postTitle=" + postTitle + ", postDate=" + postDate + ", editDate=" + editDate + ", postText="
				+ postText + ", nickname=" + nickname + ", score=" + score + ", likeUserId=" + likeUserId + ", isLike="
				+ isLike + ", likeNum=" + likeNum + "]";
	}
	
}
	