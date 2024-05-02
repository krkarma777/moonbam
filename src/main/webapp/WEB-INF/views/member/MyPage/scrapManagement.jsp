<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <title>문화인들의 밤</title>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
    <style>
        /* 기본적인 링크 스타일 */
        a {
            color: #007bff; /* 링크 색상 */
            text-decoration: none; /* 밑줄 제거 */
            transition: color 0.3s; /* 색상 변화에 애니메이션 적용 */
        }

        /* 마우스를 올렸을 때 링크 색상 변경 */
        a:hover {
            color: #0056b3; /* 변경할 색상 */
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container moonBam-container">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <div class="shadow">
          <div style="text-align: center;">
            <h1 class="mt-4">내 스크랩</h1>
            <table class="table table-striped">
                <thead class="table-danger">
                <tr>
                <th><input type="checkbox" id="selectAll"></th>
                    <th>Scrap ID</th>
                    <th>Post ID</th>
                    <th>Post Title</th>
                    <th>Scrap Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sDTO.list}" var="scrap">
                    <tr>
                    <td><input type="checkbox" class="scrapCheckbox" value="${scrap.scrapId}" ></td>
                        <td>${scrap.scrapId}</td>
                        <td>${scrap.postId}</td>
                       <td><a href="/acorn/board/content?postId=${scrap.postId}&bn=${scrap.postBoard}">${scrap.postTitle}</a></td>
                        <td><fmt:formatDate value="${scrap.scrapDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                                <a href="/acorn/my-page/scrap/${scrap.scrapId}" class="btn btn-danger">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 페이지네이션 -->
         <div class="center-align">
          <jsp:include page="MyPagenationScrap.jsp"/>      
            </div>
           <!-- 선택 삭제 및 전체 삭제 버튼 -->
            <div class="text-end">
                <!-- 선택 삭제 버튼 -->
                <button type="button" class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
            </div>  
    </div>
</div>
</div>
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
$(document).ready(function() {
    // 선택 삭제 버튼 클릭 시 선택된 게시물 삭제
    $("#deleteSelectedBtn").click(function() {
        $(".scrapCheckbox:checked").each(function() {
            var postId = $(this).val();
            // 선택된 게시물 삭제
            $.ajax({
                url: "<c:url value='/my-page/scrap/{scrapId}'/>",
                type: "POST",
                data: { scrapId: scrapId },
                success: function(response) {
                    // 삭제된 게시물의 DOM 제거
                    $("#scrap-" + scrapId).closest("tr").remove();
                    // 페이지 새로고침
                    location.reload();
                },
                error: function(xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });

    // 전체 선택 및 전체 삭제 버튼
    $("#selectAll").click(function() {
        $(".scrapCheckbox").prop('checked', $(this).prop('checked'));
    });

    // 전체 삭제 버튼 클릭 시 모든 게시물 삭제
    $("#deleteAllBtn").click(function() {
        // 모든 게시물의 postId를 담을 배열
        var allPostIds = [];

        // 각 게시물의 postId를 배열에 추가
        $(".postCheckbox").each(function() {
            var scrapId = $(this).val();
            allScrapIds.push(scrapId);
        });

        // 게시물이 없는 경우 아무것도 하지 않음
        if(allScrapIds.length === 0) {
            console.log("No scraps available for deletion.");
            return;
        }

        // 모든 게시물들을 서버로 전송하여 삭제 요청
        $.ajax({
            url: "<c:url value='/my-page/delAllScraps'/>",
            type: "POST",
            data: JSON.stringify(allScrapIds), // postId 배열을 JSON 형태로 전송
            contentType: "application/json", // 전송할 데이터의 타입 지정
            success: function(response) {
                // 모든 게시물이 삭제되면 DOM에서 해당 요소 제거
                $(".scrapCheckbox").closest("tr").remove();
                // 페이지 새로고침
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    });
});
</script>
</body>
</html>
