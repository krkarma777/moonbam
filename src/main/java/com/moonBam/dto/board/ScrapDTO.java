package com.moonBam.dto.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("ScrapDTO")
public class ScrapDTO {

	private Long scrapId;
	private String userId;
	private Long postId;
	private Date scrapDate;
	private String postTitle;
	private String postBoard; // postBoard 속성 추가
}
