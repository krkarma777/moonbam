package com.moonBam.dto;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.moonBam.dto.board.PostDTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Alias("MyScrapDTO")
public class MyScrapDTO {
	private List<PostDTO> list;   // 현재페이지에 들어갈 레코드를 perPage만큼만 저장 
	private int curPage;    //현재 볼 페이지 번호 
	private int perPage=8;  //한페이지에 보여질 목록 수 
	private int totalCount; //전체 레코드 갯수 
	
	private int perBlock=5;
}
