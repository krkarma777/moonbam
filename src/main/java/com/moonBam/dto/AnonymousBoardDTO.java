package com.moonBam.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("DebugBoardDTO")
public class AnonymousBoardDTO {

	private int boardNum;			//글 번호		//시퀸스 사용
	private String nickname;		//작성자		//로그인 시 닉네임 | 직접 입력 | 자동입력
	private String password;		//글 비밀번호
	private String title;			//글 제목
	private String category;		//글 카테고리
	private String content;			//글 내용
	private String postDate;		//작성 날짜	//글 작성 시 sysDate
	private String edittedDate;		//수정 날짜	//글 작성 시  postDate와 동일값
	private int viewCount;			//조회수		//default 0
	private int recommendNum;		//추천수		//default 0
	private int disRecommendNum;	//비추천수		//default 0
	@Override
	public String toString() {
		return "DebugBoardDTO [boardNum=" + boardNum + ", nickname=" + nickname + ", password=" + password + ", title="
				+ title + ", category=" + category + ", content=" + content + ", postDate=" + postDate
				+ ", edittedDate=" + edittedDate + ", viewCount=" + viewCount + ", recommendNum=" + recommendNum
				+ ", disRecommendNum=" + disRecommendNum + "]";
	}
	public AnonymousBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnonymousBoardDTO(int boardNum, String nickname, String password, String title, String category, String content,
			String postDate, String edittedDate, int viewCount, int recommendNum, int disRecommendNum) {
		super();
		this.boardNum = boardNum;
		this.nickname = nickname;
		this.password = password;
		this.title = title;
		this.category = category;
		this.content = content;
		this.postDate = postDate;
		this.edittedDate = edittedDate;
		this.viewCount = viewCount;
		this.recommendNum = recommendNum;
		this.disRecommendNum = disRecommendNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getEdittedDate() {
		return edittedDate;
	}
	public void setEdittedDate(String edittedDate) {
		this.edittedDate = edittedDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getRecommendNum() {
		return recommendNum;
	}
	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}
	public int getDisRecommendNum() {
		return disRecommendNum;
	}
	public void setDisRecommendNum(int disRecommendNum) {
		this.disRecommendNum = disRecommendNum;
	}
	
}

