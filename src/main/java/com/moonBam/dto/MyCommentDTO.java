package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;
import com.moonBam.dto.CommentDTO;
@Alias("MyCommentDTO")
public class MyCommentDTO {
	private List<CommentDTO> list;
	private int curPage;
	private int perPage=12;
	private int totalCount;
	
	private int perBlock=3;
	
	public int getPerBlock() {
		return perBlock;
	}
	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}
	public List<CommentDTO> getList() {
		return list;
	}
	public void setList(List<CommentDTO> list) {
		this.list = list;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
