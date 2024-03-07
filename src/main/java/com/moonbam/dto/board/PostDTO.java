package com.moonbam.dto.board;

import org.apache.ibatis.type.Alias;

import java.util.Date;
@Alias("PostDTO")
public class PostDTO implements IPost {
    private Long postId;
    private String postBoard;
    private String userId;
    private Long contId;
    private String postTitle;
    private Date postDate;
    private Date postEditDate;
    private String postText;
    private String nickname;
    private Long categoryId;
    
	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDTO(Long postId, String postBoard, String userId, Long contId, String postTitle, Date postDate,
			Date postEditDate, String postText, String nickname, Long categoryId) {
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
		this.categoryId = categoryId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", postBoard=" + postBoard + ", userId=" + userId + ", contId=" + contId
				+ ", postTitle=" + postTitle + ", postDate=" + postDate + ", postEditDate=" + postEditDate
				+ ", postText=" + postText + ", nickname=" + nickname + ", categoryId=" + categoryId + "]";
	}

    
    
}

