<%@page import="com.moonBam.dto.MoviePageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MoviePageDTO mpDTO = (MoviePageDTO)request.getAttribute("mpDTO");
    int curPage = mpDTO.getCurPage();
    int perPage = mpDTO.getPerPage();
    int totalCount = mpDTO.getTotalCount();
    
    int totalNum = totalCount/perPage;
    if(totalCount%perPage!=0) totalNum++;
    
	String searchCategory = (String)request.getAttribute("searchCategory");
	String searchValue = (String)request.getAttribute("searchValue");
  	int perBlock = mpDTO.getPerBlock();
  	
  	String communityCategory = (String)request.getAttribute("communityCategory");
	
    
 // 현재 화면에 보여질 페이지번호들의 시작페이지번호, 마지막페이지번호 구하기
    // 현재 페이지번호의 블럭번호 구하기
    int curBlock = ( int )Math.ceil( ( double )curPage / perBlock );

    // 시작페이지번호 구하기
    int startPage = ( curBlock - 1 ) * perBlock + 1;
    
    // 마지막페이지번호 구하기
    int endPage = startPage + perBlock - 1;
    if ( endPage > totalNum ) endPage = totalNum;
    
    
	// 페이지번호 출력 시작
	out.print("<ul class='pagination'>");
    // 1. 첫 페이지로 바로 이동
    if ( curPage == 1 )
        out.print( "<li class='page-item'><a class='page-link'>처음</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='movieSearch?curPage=1&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>처음</a></li>" );

    // 2. 이전 페이지로 이동
    if ( curPage == 1 )
        out.print( "<li class='page-item'><a class='page-link'>&lt;</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='movieSearch?curPage=" + ( curPage - 1 ) + "&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>&lt;</a></li>" );

    // 3. 페이지번호들
    for ( int i = startPage; i < endPage; i++  ) { // 마지막페이지번호는 표시 안 하고, for 루프 밑에서 표시( 공백 표시 때문에... )

        if ( curPage == i )
            out.print( "<li class='page-item active'><a class='page-link'>"+i+"</a></li>" );
        else
        out.print( "<li class='page-item'><a class='page-link' href='movieSearch?curPage=" + i + "&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>" + i + "</a></li>" );

    }
    if ( curPage == endPage )
        out.print( "<li class='page-item'><a class='page-link'>"+endPage+"</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link'  href='movieSearch?curPage=" + endPage + "&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>" + endPage + "</a></li>" );

    // 2. 다음 페이지로 이동
    if ( curPage == totalNum )
        out.print( "<li class='page-item'><a class='page-link'>&gt;</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='movieSearch?curPage=" + ( curPage + 1 ) + "&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>&gt;</a></li>" );

    // 1. 마지막 페이지로 바로 이동
    if ( curPage == totalNum )
        out.print( "<li class='page-item'><a class='page-link'>마지막</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='movieSearch?curPage=" + totalNum + "&searchCategory=" + searchCategory + "&searchValue=" + searchValue + "'>마지막</a></li>" );
	out.print("</ul>");
    
%>