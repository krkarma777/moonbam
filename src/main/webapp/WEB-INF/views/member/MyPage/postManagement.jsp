<%@ page import="com.moonBam.dto.board.PostDTO" %>
<%@ page import="com.moonBam.dto.MyPageDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: krkarma777
  Date: 2024-04-17
  Time: 오전 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MyPageDTO mDTO = (MyPageDTO) request.getAttribute("mDTO");
    List<PostDTO> selectMyPostPaged = mDTO.getList();
%>
<!DOCTYPE html>
<html lang="ko"><!--  -->
<head>
    <title>문화인들의 밤</title>
    <!-- Bootstrap CSS -->
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Custom CSS -->
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

        <!-- 메인 컨텐츠 시작 -->
        <div class="shadow">
            <div style="text-align: center;">
                <h1>내 게시글</h1>
            </div>
            <table  class="table table-striped ">
                <thead class="table-danger">
                <tr>
                    <th><input type="checkbox" id="selectAll"></th>
                    <th>Post ID</th>
                    <th>Post Board</th>
                    <th>Post Title</th>
                    <th>Post Date</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody >
                <c:forEach items="${mDTO.list}" var="post">
                    <tr>
                        <td><input type="checkbox" name="check" class="postCheckbox" value="${post.postId}"></td>
                        <td>${post.postId}</td>
                        <td>${post.postBoard}</td>
                        <td>
                            <a href="#" onclick="goToPostPage('${post.postId}', '${post.postBoard}', '${post.contId}')">${post.postTitle}</a>
                            <input type="hidden" id="contId_${post.postId}" value="${post.contId}">
                        </td>
                        <td><fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
                            <form action="<c:url value='/my-page/postDel'/>" method="post">
                                <input type="hidden" name="postId" value="${post.postId}">
                                <button type="submit" class="btn btn-danger delBtn">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 페이지네이션-->
            <div class="center-align">
                <jsp:include page="MyPagenation.jsp"/>
            </div>
            <!-- 전체 선택 및 전체 삭제 버튼 -->
            <div class="text-end">
                <!-- 선택 삭제 버튼 -->
                <button type="button" class="btn btn-danger" id="deleteSelectedBtn">선택 삭제</button>
            </div>
        </div>
        <!-- 메인 컨텐츠 끝 -->
    </div>
</div>
<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function goToPostPage(postId, postBoard, contId) {
        if (contId !== null && contId !== "") {
            window.location.href = "/acorn/review?postId=" + postId;
        } else {
            window.location.href = "/acorn/board/content?postId=" + postId + "&bn=" + postBoard;
        }
    }

    $(document).ready(function() {
        // 선택 삭제 버튼 클릭 시 선택된 게시물 삭제
        $("#deleteSelectedBtn").click(function() {
            $(".postCheckbox:checked").each(function() {
                var postId = $(this).val();
                // 선택된 게시물 삭제
                $.ajax({
                    url: "<c:url value='/my-page/postDel'/>",
                    type: "POST",
                    data: { postId: postId },
                    success: function(response) {
                        // 삭제된 게시물의 DOM 제거
                        $("#post-" + postId).closest("tr").remove();
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
            $(".postCheckbox").prop('checked', $(this).prop('checked'));
        });

        // 전체 삭제 버튼 클릭 시 모든 게시물 삭제
        $("#deleteAllBtn").click(function() {
            // 모든 게시물의 postId를 담을 배열
            var allPostIds = [];

            // 각 게시물의 postId를 배열에 추가
            $(".postCheckbox").each(function() {
                var postId = $(this).val();
                allPostIds.push(postId);
            });

            // 게시물이 없는 경우 아무것도 하지 않음
            if(allPostIds.length === 0) {
                console.log("No posts available for deletion.");
                return;
            }

            // 모든 게시물들을 서버로 전송하여 삭제 요청
            $.ajax({
                url: "<c:url value='/my-page/delAllPosts'/>",
                type: "POST",
                data: JSON.stringify(allPostIds), // postId 배열을 JSON 형태로 전송
                contentType: "application/json", // 전송할 데이터의 타입 지정
                success: function(response) {
                    // 모든 게시물이 삭제되면 DOM에서 해당 요소 제거
                    $(".postCheckbox").closest("tr").remove();
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
