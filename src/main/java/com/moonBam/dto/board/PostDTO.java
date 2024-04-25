package com.moonBam.dto.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@ToString
@Alias("PostDTO")
public class PostDTO implements IPost {

    private Long postId;

	@NotBlank
    private String postBoard;

    private String userId;

    private Long contId;

	@NotBlank(message = "제목을 입력해주세요.")
	@Size(max = 40, message = "제목 길이가 너무 깁니다.")
    private String postTitle;

    private Date postDate;

    private Date postEditDate;

	@NotBlank(message = "글 내용을 입력해주세요.")
    private String postText;

    private String nickname;

	@NotNull
    private Long categoryId;
    
	public PostDTO() {
	}

	public PostDTO(Long postId, String postBoard, String userId, Long contId, String postTitle, Date postDate,
			Date postEditDate, String postText, String nickname, Long categoryId) {
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
}

