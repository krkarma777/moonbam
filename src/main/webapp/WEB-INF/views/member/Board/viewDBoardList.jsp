<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 글 목록 페이지</title>
    <!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- 외부 CSS -->
    <style>
        body {
            padding: 20px;
            max-width: 1080px;
            margin: auto;
        }
        .btn-group {
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 0;
        }
        #listHead{
        	font-size: 12px;
        	vertical-align: middle;
        	white-space: nowrap; 
        }	
	    #timeText{
        	font-size: 13px;
        	vertical-align: middle;
        	white-space: nowrap; 
        }  
        #nameText{
        	font-size: 13px;
        	vertical-align: middle;
        	white-space: nowrap; 
        }
        #titleText{
        	cursor: pointer;
           display: -webkit-box;
            -webkit-line-clamp: 1; 					/* 한 칸에 표시할 줄 수 */
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
        .badge {
       	 	padding: 8px 10px;
   		}
	      
    </style>
</head>
<body>
    <!-- 헤더 네비게이션바 -->
    <div id="header">
        <jsp:include page="/WEB-INF/views/common/navibarForMember.jsp" flush="true"></jsp:include><br>
 		<jsp:include page="/WEB-INF/views/common/categoryNavForDBoard.jsp" flush="true"></jsp:include>
    </div>
    
	
	<!-- 게시판 글 리스트업 -->
	<div class="card text-center">
	    <div class="card-body">
	        <table class="table table-striped">
				 <thead>
				    <tr id="listHead">
				        <th class="col-1">글번호</th>
				        <th class="col-1"  id="cateHeadText">카테고리</th>
				        <th class="col-auto">제목</th>
				        <th class="col-1">닉네임</th>
				        <th class="col-1">작성 날짜</th>
				        <th class="col-1">조회수</th>
				        <th class="col-1">추천수</th>
				    </tr>
				</thead>
	            <tbody>
	                <c:forEach var="db" items="${list}" varStatus="vs">
	                    <tr>
	                        <td><span class="badge bg-info"><c:out value="${db.boardNum}"/></span></td>
	                        <td><span class="badge bg-secondary"><c:out value="${db.category}"/></span></td>
	                        <td class="text-start"><span id="titleText" class="link-primary" onclick="submitForm(${db.boardNum})">${db.title}</span></td>
	                        <td id="nameText"><c:out value="${db.nickname}"/></td>
	                        <td id="timeText"><c:out value="${db.edittedDate}"/></td>
	                        <td><span class="badge bg-warning text-dark"><c:out value="${db.viewCount > 9999 ? '9999+' : db.viewCount}"/></span></td>
							<td><span class="badge bg-success"><c:out value="${Math.min(Math.max(db.recommendNum - db.disRecommendNum, -999), 999)}"/></span></td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>

    <!-- 페이지 번호 표시 -->
    <nav aria-label="페이지 네비게이션">
        <ul class="pagination justify-content-center">
            <!-- 이전 버튼 -->
            <li class="page-item">
                <c:if test="${currentPage > 1}">
                    <c:url var="prevPageURL" value="/viewDBoardList">
                        <c:param name="currentPage" value="${currentPage - 1}"/>
                        <c:param name="perPage" value="${perPage}"/>
                        <c:param name="orderBy" value="${orderBy}"/>
                    </c:url>
                    <a class="page-link" href="${prevPageURL}" aria-label="Previous">이전</a>
                </c:if>
            </li>
            <!-- 페이지 번호 -->
            <c:choose>
			    <c:when test="${currentPage <= 5}">
			        <c:set var="startPage" value="1" />
			    </c:when>
			    <c:otherwise>
			        <c:set var="startPage" value="${currentPage - 5}" />
			    </c:otherwise>
			</c:choose>
            <c:forEach begin="${startPage}" end="${startPage + 9}" var="pageNum">
                <c:if test="${pageNum > 0 && pageNum <= (totalPosts + perPage - 1) / perPage}">
                    <li class="page-item">
                        <c:url var="pageURL" value="/viewDBoardList">
                            <c:param name="currentPage" value="${pageNum}"/>
                            <c:param name="perPage" value="${perPage}"/>
                            <c:param name="orderBy" value="${orderBy}"/>
                        </c:url>
                        <a class="page-link" href="${pageURL}">${pageNum}</a>
                    </li>
                </c:if>
            </c:forEach>
            <!-- 다음 버튼 -->
            <li class="page-item">
                <c:if test="${currentPage < (totalPosts + perPage - 1) / perPage -1}">
                    <c:url var="nextPageURL" value="/viewDBoardList">
                        <c:param name="currentPage" value="${currentPage + 1}"/>
                        <c:param name="perPage" value="${perPage}"/>
                        <c:param name="orderBy" value="${orderBy}"/>
                    </c:url>
                    <a class="page-link" href="${nextPageURL}" aria-label="Next">다음</a>
                </c:if>
            </li>
        </ul>
    </nav>


<table width="100%">
    <tr>
        <td width="30%"></td>
        <td width="60%">
            <!-- 글 검색 -->
            <form action="<c:url value='/searchPost'/>" method="post" class="d-flex">
                <input type="hidden" name="orderBy" value="${orderBy}">
                <select class="col-auto" name="searchTag" style="height: 38px;">
                    <option value="title_contents">제목 + 내용</option>
                    <option value="title">제목</option>
                    <option value="contents">내용</option>
                    <option value="nickname">닉네임</option>
                </select>
                <div class="col-auto">&nbsp;</div>
                <input class="col-auto" type="text" name="searchData" style="height: 38px;">
                <div class="col-auto">&nbsp;</div>
                <button class="btn btn-primary col-auto" type="submit">검색</button>
            </form>
        </td>
        <td width="10%"></td>
        <td width="10%">
            <!-- 게시판 글쓰기 -->
            <form action="<c:url value='newPost'/>" method="post">
                <input type="submit" id="insert" value="글 작성" class="btn btn-success">
            </form>
        </td>
    </tr>
</table>









<script type="text/javascript">

	//글 보기로 이동
	function submitForm(boardNum) {
        window.location.href ="<c:url value='/viewDBoardContent'/>?boardNum="+boardNum;
    }

	//게시판 리스트 변경
	function changeList(e) {
		var orderBy = e.value
        window.location.href ="<c:url value='/viewDBoardList'/>?orderBy="+orderBy;
    }
	
	//게시판 탭 변경
	function changeTab(e) {
		var orderBy = e.innerText
        window.location.href ="<c:url value='/viewDBoardList'/>?orderBy="+orderBy;
    }

</script>

<!-- 부트 스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>

</html>
