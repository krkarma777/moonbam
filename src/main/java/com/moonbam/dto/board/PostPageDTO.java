package com.moonBam.dto.board;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("PostPageDTO")
public class PostPageDTO implements IPost {
    private Long postId;
    private String postBoard;
    private String userId;
    private Long contId;
    private String postTitle;
    private Date postDate;
    private Date postEditDate;
    private String postText;
    private String nickname;
    private Long viewNum;
    private Long likeNum;
    private Long commentCount; // 댓글 수를 위한 새로운 필드
    private String categoryName;
    
	public PostPageDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Date getPostEditDate() {
		return postEditDate;
	}

	public void setPostEditDate(Date postEditDate) {
		this.postEditDate = postEditDate;
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

	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}

	public Long getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Long likeNum) {
		this.likeNum = likeNum;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public PostPageDTO(Long postId, String postBoard, String userId, Long contId, String postTitle, Date postDate,
			Date postEditDate, String postText, String nickname, Long viewNum, Long likeNum, Long commentCount,
			String categoryName) {
		super();
		this.postId = postId;
		this.postBoard = postBoard;
		this.userId = userId;
		this.contId = contId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.postEditDate = postEditDate;
		this.postText = postText;
		this.nickname = nickname;
		this.viewNum = viewNum;
		this.likeNum = likeNum;
		this.commentCount = commentCount;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "PostPageDTO [postId=" + postId + ", postBoard=" + postBoard + ", userId=" + userId + ", contId="
				+ contId + ", postTitle=" + postTitle + ", postDate=" + postDate + ", postEditDate=" + postEditDate
				+ ", postText=" + postText + ", nickname=" + nickname + ", viewNum=" + viewNum + ", likeNum=" + likeNum
				+ ", commentCount=" + commentCount + ", categoryName=" + categoryName + "]";
	}

	
    
    
}

