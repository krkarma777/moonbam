package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

@Alias("CommunityBadWordsDTO")
public class CommunityBadWordsDTO {

	private String badWords;

	public CommunityBadWordsDTO(String badWords) {
		super();
		this.badWords = badWords;
	}

	public String getBadWords() {
		return badWords;
	}

	public void setBadWords(String badWords) {
		this.badWords = badWords;
	}

	@Override
	public String toString() {
		return "CommunityBadWords [badWords=" + badWords + "]";
	}

	public CommunityBadWordsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
