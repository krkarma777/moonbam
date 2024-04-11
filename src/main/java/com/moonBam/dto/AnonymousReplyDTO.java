package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Alias("AnonymousReplyDTO")
public class AnonymousReplyDTO {

	private int anonymousReplyNum; 		//대댓글 번호		//시퀸스 사용
	private String replyNickname;		//대댓글 작성자		
	private String replyPassword;		//대댓글 비밀번호	
	private String replyContent;		//대댓글 내용		
	private String replyPostDate;		//작성 날짜		//댓글 작성 시점	sysDate
	private String replyEdittedDate;	//수정 날짜		//댓글 작성 시		postDate와 동일값
	private int anonymousCommentNum;	//댓글 번호		//참조

	public AnonymousReplyDTO() {
		super();
	}

	public AnonymousReplyDTO(int anonymousReplyNum, String replyNickname, String replyPassword, String replyContent,
			String replyPostDate, String replyEdittedDate, int anonymousCommentNum) {
		super();
		this.anonymousReplyNum = anonymousReplyNum;
		this.replyNickname = replyNickname;
		this.replyPassword = replyPassword;
		this.replyContent = replyContent;
		this.replyPostDate = replyPostDate;
		this.replyEdittedDate = replyEdittedDate;
		this.anonymousCommentNum = anonymousCommentNum;
	}

	
}

