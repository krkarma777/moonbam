package com.moonBam.dto.board;


import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("PageDTO")
public class PageDTO<T> {
    private List<T> list; // 현재 페이지에 해당하는 데이터 목록
    private int curPage; // 현재 페이지 번호
    private int perPage; // 페이지당 표시할 데이터 수
    private int totalCount; // 전체 데이터 수

    public List<T> getList() {
        return list;
    }

    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    @Override
	public String toString() {
		return "PageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ "]";
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
