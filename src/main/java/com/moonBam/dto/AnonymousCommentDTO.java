package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("AnonymousCommentDTO")
public class AnonymousCommentDTO {

	private int anonymousCommentNum; 	//댓글 번호		//시퀸스 사용
	private String commentNickname;		//댓글 작성자		//직접 입력
	private String commentPassword;		//댓글 비밀번호
	private String commentContent;		//댓글 내용
	private String commentPostDate;		//작성 날짜		//댓글 작성 시점	sysDate
	private String commentEdittedDate;	//수정 날짜		//댓글 작성 시		postDate와 동일값
	private int boardNum;				//글 번호			//참조

	public AnonymousCommentDTO() {
		super();
	}

	public AnonymousCommentDTO(int anonymousCommentNum, String commentNickname, String commentPassword,
			String commentContent, String commentPostDate, String commentEdittedDate, int boardNum) {
		super();
		this.anonymousCommentNum = anonymousCommentNum;
		this.commentNickname = commentNickname;
		this.commentPassword = commentPassword;
		this.commentContent = commentContent;
		this.commentPostDate = commentPostDate;
		this.commentEdittedDate = commentEdittedDate;
		this.boardNum = boardNum;
	}
	
	
}

