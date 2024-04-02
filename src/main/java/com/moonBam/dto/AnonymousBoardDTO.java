package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
	
}

