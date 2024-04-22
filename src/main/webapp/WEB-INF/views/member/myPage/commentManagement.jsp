<%@ page import="com.moonBam.dto.CommentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>문화인들의 밤</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="/acorn/resources/css/myPage.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/navBar.jsp"/>
<div class="container moonBam-container">
    <div class="row">
        <jsp:include page="sideBar.jsp" flush="true"/>

        <!-- 메인 컨텐츠 시작 -->
        <div class="shadow">
            <div style="text-align: center;">
                <h1>내 댓글</h1>
                <table class="table table-striped">
                    <thead>
                    <tr>
                     <th><input type="checkbox" id="selectAll"></th>
                        <th>Comment ID</th>
                        <th>Post ID</th>
                        <th>Comment Date</th>
                        <th>Comment Text</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cDTO.list}" var="comment">
                        <tr>
                          <td><input type="checkbox" class="commentCheckbox" value="${comment.comId}" data-above-com-id="${comment.aboveComId}" ></td>
                            <td>${comment.comId}</td>
                            <td>${comment.postId}</td>
                            <td>${comment.comDate}</td>
                            <td>${comment.comText}</td>
                            <td><button class="btn btn-danger delBtn" data-comment-id="${comment.comId}" data-above-com-id="${comment.aboveComId}"">삭제</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- 페이지네이션-->
            <div class="center-align">
            <jsp:include page="MyPagenationComm.jsp"/>
            </div>
              <!-- 선택 삭제 및 전체 삭제 버튼 -->
            <div class="text-end">
                <!-- 선택 삭제 버튼 -->
                <button type="button" class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
                <!-- 전체 삭제 버튼 -->
                <button type="button" class="btn btn-danger" id="deleteAllBtn">전체 삭제</button>
            </div>
        </div>
        <!-- 메인 컨텐츠 끝 -->
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
$(document).ready(function() {
    // 삭제 버튼 클릭 시
    $(".delBtn").click(function() {
        // 해당 댓글의 ID와 aboveComId 가져오기
        var commentId = $(this).data("comment-id");
        var aboveComId = $(this).data("above-com-id");

        // 콘솔에 aboveComId 출력 (디버깅용)
        console.log("aboveComId: " + aboveComId);
        // AJAX를 통해 댓글 삭제 요청 보내기
        $.ajax({
            type: "POST",
            url: "<c:url value='/my-page/commDel'/>",
            data: {
                comId: commentId,
                aboveComId: aboveComId
            },
            success: function(response) {
                alert(response); // 응답 메시지 출력
                location.reload(); // 페이지 새로고침
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText); // 에러 메시지 출력
            }
        });
    });
    
 // 전체 선택 및 해제
    $("#selectAll").click(function() {
        $(".commentCheckbox").prop("checked", $(this).prop("checked"));
    });

    // 선택 삭제 버튼 클릭 시 선택된 댓글 삭제
    $("#deleteSelectedBtn").click(function() {
        $(".commentCheckbox:checked").each(function() {
            var commentId = $(this).val();
            console.log(commentId);
            // 선택된 댓글 삭제
            $.ajax({
                type: "POST",
                url: "<c:url value='/my-page/commDel'/>",
                data: {
                    comId: commentId,
                    aboveComId: $(this).data("above-com-id")
                },
                success: function(response) {
                    alert(response); // 응답 메시지 출력
                    location.reload(); // 페이지 새로고침
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText); // 에러 메시지 출력
                }
            });
        });
    });

    // 전체 삭제 버튼 클릭 시 모든 댓글 삭제
    $("#deleteAllBtn").click(function() {
    	 $(".commentCheckbox:checked").each(function() {
             var commentId = $(this).val();
             // 선택된 댓글 삭제
             $.ajax({
                 type: "POST",
                 url: "<c:url value='/my-page/commDel'/>",
                 data: {
                     comId: commentId,
                     aboveComId: $(this).data("above-com-id")
                 },
                 success: function(response) {
                     alert(response); // 응답 메시지 출력
                     location.reload(); // 페이지 새로고침
                 },
                 error: function(xhr, status, error) {
                     console.error(xhr.responseText); // 에러 메시지 출력
                 }
             });
         });
     });



});
</script>
</body>
</html>
