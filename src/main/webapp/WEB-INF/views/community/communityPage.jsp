<%@page import="com.moonBam.dto.CommunityPageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    CommunityPageDTO cpDTO = (CommunityPageDTO)request.getAttribute("cpDTO");
    int curPage = cpDTO.getCurPage();
    int perPage = cpDTO.getPerPage();
    int totalCount = cpDTO.getTotalCount();
    
    int totalNum = totalCount/perPage;
    if(totalCount%perPage!=0) totalNum++;
    
	String searchName = (String)request.getAttribute("searchName");
	String searchValue = (String)request.getAttribute("searchValue");
  	int perBlock = cpDTO.getPerBlock();
  	
  	String communityCategory = (String)request.getAttribute("communityCategory");
  	System.out.println("in communityPage.jsp: "+ communityCategory);
	
   /*  for(int i = 1 ; i  <= totalNum ; i++){
    	if(curPage == i){
    		out.print(i+"&nbsp;");
    	}else{
    		out.print( "<a href='MyBoardListServlet?curPage="+i+"&searchName="+searchName+"&searchValue="+searchValue+"'>"+i  +"</a>&nbsp;" );
    	}
    	
    	
    } */
    
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
        out.print( "<li class='page-item'><a class='page-link' href='javascript:toSearch(1, "+ searchName +", "+ searchValue +")'>처음</a></li>" );

    // 2. 이전 페이지로 이동
    if ( curPage == 1 )
        out.print( "<li class='page-item'><a class='page-link'>&lt;</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='javascript:toSearch("+(curPage -1)+", "+ searchName +", "+ searchValue +")'>&lt;</a></li>" );

    // 3. 페이지번호들
    for ( int i = startPage; i < endPage; i++  ) { // 마지막페이지번호는 표시 안 하고, for 루프 밑에서 표시( 공백 표시 때문에... )

        if ( curPage == i )
            out.print( "<li class='page-item active'><a class='page-link'>"+i+"</a></li>" );
        else
        out.print( "<li class='page-item'><a class='page-link' href='javascript:toSearch("+i+", "+ searchName +", "+ searchValue +")'>" + i + "</a></li>" );

    }
    if ( curPage == endPage )
        out.print( "<li class='page-item'><a class='page-link'>"+endPage+"</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link'  href='javascript:toSearch("+endPage+", "+ searchName +", "+ searchValue +")'>" + endPage + "</a></li>" );

    // 2. 다음 페이지로 이동
    if ( curPage == totalNum )
        out.print( "<li class='page-item'><a class='page-link'>&gt;</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='javascript:toSearch("+(curPage+1)+", "+ searchName +", "+ searchValue +")'>&gt;</a></li>" );

    // 1. 마지막 페이지로 바로 이동
    if ( curPage == totalNum )
        out.print( "<li class='page-item'><a class='page-link'>마지막</a></li>" );
    else
        out.print( "<li class='page-item'><a class='page-link' href='javascript:toSearch("+totalNum+", "+ searchName +", "+ searchValue +")'>마지막</a></li>" );
	out.print("</ul>");
    /* " + communityCategory + "?curPage=" + totalNum + "&searchName=" + searchName + "&searchValue=" + searchValue + " */
%>

<script>
	function toSearch(curPage, searchName, searchValue){
		console.log("toSearch")
		let form = document.createElement('form');
		
		let curPageObj;
		curPageObj = document.createElement('input');
		curPageObj.setAttribute('type', 'hidden');
		curPageObj.setAttribute('name', 'curPage');
		curPageObj.setAttribute('value', curPage);
		
		let searchNameObj;
		searchNameObj = document.createElement('input');
		searchNameObj.setAttribute('type', 'hidden');
		searchNameObj.setAttribute('name', 'searchName');
		searchNameObj.setAttribute('value', searchName);
		
		let searchValueObj;
		searchValueObj = document.createElement('input');
		searchValueObj.setAttribute('type', 'hidden');
		searchValueObj.setAttribute('name', 'searchValueObj');
		searchValueObj.setAttribute('value', searchValueObj);
		
		form.appendChild(curPageObj);
		form.appendChild(searchNameObj);
		form.appendChild(searchValueObj);
	    form.setAttribute('method', 'post');
	    form.setAttribute('action', '<%=communityCategory %>');
	    document.body.appendChild(form);
	    form.submit();
	}
</script>