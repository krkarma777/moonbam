package com.moonBam.controller.board.util;

public class ViewService {

	
	public String BoardNameCategory(String postBoard) {
		String boardCategory = "";

		if(postBoard.contains("movie")){
			boardCategory += "영화";
		} else if (postBoard.contains("tv")) {
			boardCategory += "방송";
		} else if (postBoard.contains("book")){
			boardCategory += "책";
		}
	
		return boardCategory;
	}
	public String BoardName(String postBoard) {
		String boardName = "";
		
		if (postBoard.contains("Info")) {
			boardName += "정보";
		} else if (postBoard.contains("Meet")){
			boardName += "모임";
		} else {
			boardName += "자유";
		}
		
		return boardName;
	}
	
	public String linkMainCategory(String postBoard) {
		String link = "";

		if(postBoard.contains("movie")){
			link += "main?cg=movie";
		} else if (postBoard.contains("tv")) {
			link += "main?cg=tv";
		} else if (postBoard.contains("book")){
			link += "main?cg=book";
		}
	
		return link;
	}
	public String linkDropDownCategory(String postBoard) {
		String link = "";
		
		if(postBoard.contains("movie")){
			link += "movie";
		} else if (postBoard.contains("tv")) {
			link += "tv";
		} else if (postBoard.contains("book")){
			link += "book";
		}
		
		return link;
	}
}
