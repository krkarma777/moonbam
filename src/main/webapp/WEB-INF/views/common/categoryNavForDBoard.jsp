<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 게시판, 글 네비게이션 -->
<div class="row">
    <!-- 글 정렬 -->
	<div class="col">
	    <div class="btn-group" role="group" aria-label="글 정렬">
	        <button type="button" class="btn btn-outline-primary" name="orderBy" value="boardNum" onClick="changeList(this)">일반</button>
	        <button type="button" class="btn btn-outline-primary" name="orderBy" value="viewCount" onClick="changeList(this)">인기</button>
	        <button type="button" class="btn btn-outline-primary" name="orderBy" value="recommendNum" onClick="changeList(this)">추천</button>
	    </div>
	</div>
    
    <!-- 카테고리 검색 -->
    <div class="col-auto">
        <div class="mb-3">
            <span class="badge bg-secondary" onClick="changeTab(this)">정보</span>
            <span class="badge bg-secondary" onClick="changeTab(this)">문제</span>
            <span class="badge bg-secondary" onClick="changeTab(this)">질문</span>
            <span class="badge bg-secondary" onClick="changeTab(this)">정리</span>
            <span class="badge bg-secondary" onClick="changeTab(this)">잡담</span>
        </div>
    </div>
</div>
