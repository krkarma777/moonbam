<%@page import="com.moonBam.controller.board.util.MoviePoster" %>
<%@page import="com.moonBam.controller.board.util.ContentDataFormating" %>
<%@page import="com.moonBam.controller.board.util.ViewService" %>
<%@page import="com.moonBam.dto.board.PostPageDTO" %>
<%@page import="com.moonBam.dto.board.PageDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String strToday = sdfDate.format(now);

        PageDTO<PostPageDTO> pDTO = (PageDTO<PostPageDTO>) request.getAttribute("pDTO");
        List<PostPageDTO> list = null;
        if (pDTO != null) {
            list = pDTO.getList();
        }
        String postBoard = (String) request.getAttribute("postBoard");
        String sortIndex = request.getParameter("sortIndex");
        String selectSearchPositionText = request.getParameter("selectSearchPositionText");
        String inputSearchFreeText = request.getParameter("inputSearchFreeText");

        List<PostPageDTO> hotList = (List<PostPageDTO>) request.getAttribute("hotList");
        ContentDataFormating cdf = new ContentDataFormating();


        ViewService service = new ViewService();

        String boardName = service.BoardName(postBoard);
        String category = service.BoardNameCategory(postBoard);
        String link = service.linkMainCategory(postBoard);
        String boardType = service.linkDropDownCategory(postBoard);

    %>


    <title><%=postBoard%> Board</title>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
            rel="stylesheet">
    <!-- jQuery -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <style>
        .list-group {
            max-width: 1000px;
            margin: auto;
        }

        /* 페이징 글자색 검정으로 변경 */
        .font-black {
            color: black;
        }

        /* 선택된 페이징 번호 빨강으로 강조 */
        .font-red {
            color: red;
        }

        /* 페이징 밑줄 제거 */
        .no-underline {
            text-decoration: none;
        }

        /* 컨테이너에 상단 패딩 추가 네비게이션바 글 간격 조정 */
        .container {
            padding-top: 5px; /* 네비게이션바 높이에 따라 조정 */
        }

        /* 검색창 크기 조절 */
        .searchInput {
            width: 70vh;
        }

        /* 게시글 목록 항목 높이 조절 */
        .list-group-item {
            padding: 5px; /* 게시글 목록 높이를 조절하기 위한 padding 값 조절. */
            font-size: 12px; /* 필요에 따라 폰트 크기를 조절할 수도 있습니다. */
        }

        /* 버튼 크기 조절 */
        .custom-btn {
            padding: 4px 8px; /* 버튼의 내부 여백 조절 */
            font-size: 12px; /* 버튼 내 텍스트의 폰트 크기 조절 */
            /* height: 40px; 추가적으로 높이를 조절하고 싶은 경우 */
            /* width: 100px; 추가적으로 너비를 조절하고 싶은 경우 */
        }

        /* 게시글 목록과 버튼 사이의 간격 조절 */
        .margin-top {
            margin-top: 10px; /* 위쪽 여백 20px 추가 */
        }

        /* 네비게이션바 고정 */
        .fixed-top {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1030; /* 다른 요소들 위에 표시되도록 z-index 설정 */
        }

        /* 테이블 둥근 모서리  */
        .list-group-item {
            /* 기존 스타일 유지, 모서리 둥글게 설정 추가 */
            padding: 5px;
            font-size: 12px;
            border-radius: 10px; /* 둥근 모서리 반경 조절 */
        }

        /* 테이블 헤더 스타일 */
        .table-header {
            background-color: #f8f9fa; /* 배경색 */
            font-weight: bold; /* 글씨 굵게 */
        }

        /* 중앙 정렬을 위한 새로운 클래스 정의 */
        .text-center-align {
            text-align: center;
        }

        /* 글 제목과 댓글수 사이의 간격 조절 클래스 정의 */
        .post-title {
            margin-right: 2px; /* 오른쪽 여백 추가 */
            color: black;
            text-decoration: none;
        }

        /* 검색창과 페이지네이션 간 간격 조절 */
        .search-bar {
            margin-top: 20px; /* 검색창 상단 여백 */
            margin-bottom: 20px; /* 검색창 하단 여백 */
        }

        /* 검색창 내부의 높이 조절 */
        .form-select, .form-control {
            height: 38px; /* 검색창과 셀렉트 박스의 높이 조절 */
        }

        /* 페이지네이션과 게시글 목록 간 간격 조절 */
        .page-numbers {
            margin-top: 20px; /* 페이지네이션 상단 여백 */
        }

        /* 검색창과 글쓰기 버튼 크기 조절 */
        .search-form-control, .search-button, .write-button {
            height: 38px; /* 버튼과 입력 필드 높이 동일하게 조정 */
        }

        /* 글쓰기 버튼 색상 조절 */
        .write-button {
            background-color: #ffffff; /* 배경색 흰색 */
            color: #000000; /* 글자색 검은색 */
            border: 1px solid #000000; /* 검은색 테두리 */
        }

        /* 검색 버튼 색상 조절 */
        .search-button {
            background-color: #ffffff; /* 배경색 흰색 */
            color: #28a745; /* 글자색 초록색 */
            border: 1px solid #28a745; /* 초록색 테두리 */
        }

        /* 인라인 배치 조정 */
        .search-write-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* 검색창 너비 조절 */
        .search-input-group {
            margin-right: 50px; /* 검색창과 글쓰기 버튼 사이의 간격 */
        }

        .pagination {
            justify-content: center; /* Flexbox를 사용하여 중앙 정렬 */
        }

        /* 페이지네이션 버튼 스타일 변경 */
        .pagination .page-link {
            border: none; /* 테두리 제거 */
            background-color: #28a745; /* 기본 배경색을 초록색으로 변경 */
            color: white; /* 글자색 변경 */
        }

        /* 활성화된 페이지 번호 스타일 */
        .pagination .page-item.active .page-link {
            background-color: #218838; /* 좀 더 진한 초록색으로 변경 */
            background-image: linear-gradient(180deg, #218838, #28a745);
            /* 그라디언트 효과 */
            border: none; /* 테두리 제거 */
        }

        /* 페이지 번호에 마우스를 올렸을 때 스타일 */
        .pagination .page-link:hover {
            background-color: #1e7e34; /* 마우스 호버 시 초록색 변화 */
            background-image: linear-gradient(180deg, #1e7e34, #218838);
            /* 그라디언트 효과 */
            color: white; /* 글자색 유지 */
        }

        /* 기존 스타일에 추가 */
        .movie-poster {
            width: 100%; /* 포스터 이미지의 너비를 조정 */
            height: auto; /* 포스터 이미지의 높이를 자동 조정하여 비율 유지 */
            margin-bottom: 15px; /* 포스터 간의 간격 조정 */
        }

        /* 게시글 목록 섹션 너비 조정 */
        .list-group {
            max-width: 100%; /* 최대 너비를 100%로 조정하여 전체 너비 사용 */
        }

        .slider-container {
            overflow: hidden;
            height: 820px; /* 각 이미지 높이(225px) * 5 */
            cursor: grab; /* 클릭 가능함을 나타내는 커서 스타일 */
        }

        .slide-image {
            transition: transform 0.5s ease; /* 부드러운 애니메이션 효과 */
        }

        .movie-slide {
            position: relative;
        }

        .new-hot-label {
            font-size: 15px;
            color: #ff6600;
            margin-top: 20px;
            margin-right: 12px;
        }

        .index-label {
            position: absolute;
            bottom: -13px; /* 원하는 위치로 조절하세요. */
            left: -8px; /* 원하는 위치로 조절하세요. */
            padding: 0px 3px;
            border-radius: 5px;
            font-size: 60px;
            text-shadow: 3px 3px 6px rgba(0, 0, 0, 1);
            color: white;
            font-style: italic;
            /*   -webkit-text-stroke: 1px #fff; */
            font-family: 'TheJamsil5Bold';
        }

        @font-face {
            font-family: 'TheJamsil5Bold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
            font-weight: 700;
            font-style: normal;
        }

        /* 글씨체 적용 */
        @font-face {
            font-family: 'Pretendard-Regular';
            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
            font-weight: 400;
            font-style: normal;
        }

        body {
            font-family: 'Pretendard-Regular';
        }

        .btn-group {
            display: inline-flex; /* 버튼들을 인라인으로 배열 */
            border-radius: 10px; /* 모서리 둥글게 */
            overflow: hidden; /* 모서리를 둥글게 한 경계 밖의 내용 숨김 */
            border: 1px solid #ccc; /* 경계선 추가 */
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .shortcut-container {
            border: 1px solid #ddd; /* 경계선을 더 세밀하고 부드럽게 */
            background-color: #f9f9f9; /* 배경색을 더욱 깔끔하게 */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 추가 */
            width: 500px;
            height: 45px;
            margin: auto;
            overflow-y: hidden; /* 스크롤바를 숨깁니다 */
            border-radius: 8px; /* 모서리를 둥글게 */
            padding: 10px;
            justify-content: space-around;
            font-size: 14px; /* 기본 글자 크기 설정 */
            margin-bottom: 50px;
        }

        /* 화면 크기에 따라 컨테이너의 폭 조정을 위한 미디어 쿼리 추가 */
        @media ( max-width: 700px) {
            .shortcut-container {
                width: 100%; /* 화면이 700px 이하일 때 컨테이너 폭을 100%로 조정 */
                margin-top: 6px;
                padding: 10px;
            }
        }

        .shortcut-list li {
            margin-bottom: 5px;
        }

        .shortcut-key {
            display: inline-block;
            width: 50px;
            color: #ffffff; /* 키 배경색 */
            background-color: #fd7e14; /* 부트스트랩의 기본 파란색 */
            border-radius: 4px; /* 키 모서리 둥글게 */
            font-weight: bold;
            padding: 2px 5px;
            border: 1px solid #ddd;
            text-align: center;
            margin-right: 3px;
        }

        .shortcut-key2 {
            display: inline-block;
            width: 20px;
            color: #ffffff; /* 키 배경색 */
            background-color: #fd7e14; /* 부트스트랩의 기본 파란색 */
            border-radius: 4px; /* 키 모서리 둥글게 */
            font-weight: bold;
            padding: 2px 5px;
            border: 1px solid #ddd;
            text-align: center;
            margin-right: 3px;
        }

        .shortcut-description {
            display: inline-block;
        }

        .btn-success {
            white-space: nowrap; /* 텍스트를 한 줄에 표시 */
        }

        /* 카테고리와 게시판 이름 스타일 */
        .category-and-board-name {
            font-size: 30px; /* 크기 조절 */
            font-weight: bold; /* 글자 굵기 */
        }

        .site-footer {
            background-color: #f2f2f2;
            padding: 20px 0;
            font-size: 14px;
            line-height: 1.5;
            color: #000;
            margin-top: 20px;
        }

        .footer-content {
            text-align: center;
            max-width: 1200px;
            margin: 0 auto;
        }

        .footer-links {
            list-style: none;
            padding: 0;
            margin: 0 0 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .footer-links li {
            margin: 0 10px;
        }

        .footer-links a {
            color: #000;
            text-decoration: none;
        }

        .footer-links a:hover {
            text-decoration: underline;
        }

        .footer-contact {
            margin-bottom: 10px;
        }

        .footer-contact a {
            color: #000;
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .footer-links {
                flex-direction: column;
            }

            .footer-links li {
                margin: 5px 0;
            }
        }

        /* 드롭다운 화살표 숨기기 */
        .dropdown-toggle::after {
            display: none;
        }

        .comment-count {
            font-weight: bold; /* 글씨 굵게 */
            font-size: 10px;
            color: green;
        }

        .like-num {
            color: blue;
        }

        .popular-boards-container {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            max-width: 100%;
            margin: 0 auto;
        }


        .popular-board-header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 10px;
            font-size: 18px;
            margin-bottom: 10px;
        }

        .popular-board {
            width: calc(50% - 20px); /* 20px 여백 추가 */
            background: #f1f1f1;
            margin: 10px;
            padding: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }


        .popular-board-item {
            background: white;
            padding: 10px;
            margin-bottom: 5px;
            border: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .popular-board-item:nth-child(odd) {
            background-color: #f2f2f2;
        }

        .popular-board-item:nth-child(even) {
            background-color: #ffffff;
        }

        .popular-board-item:last-child {
            border-bottom: none;
        }

        .popular-board-item span {
            flex: 1;
            text-align: center;
        }

        .popular-board-footer {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 10px;
        }

        .popular-board-item .col-md-7 {
            background-color: #f9f9f9; /* 배경색 변경 */
            border: 1px solid #ddd; /* 테두리 스타일 추가 */
            border-radius: 5px; /* 둥근 테두리 추가 */
            padding: 10px;
            text-align: center;
        }

        .post-thumbnail-picture {
            height: 13px;
            width: 17px;
            padding-right: 5px;
        }

        .post-thumbnail-video {
            height: 12px;
            width: 13px;
        }


    </style>
</head>
<script>
    $(document).ready(function () {
        var slider = $('.slide1'); // Change this to the appropriate slider class
        var isDown = false;
        var startY;
        var scrollTop;

        slider.on('mousedown', function (e) {
            isDown = true;
            slider.addClass('active');
            startY = e.pageY - slider.offset().top;
            scrollTop = slider.scrollTop();
        });

        slider.on('mouseleave', function () {
            isDown = false;
            slider.removeClass('active');
        });

        slider.on('mouseup', function () {
            isDown = false;
            slider.removeClass('active');
        });

        slider.on('mousemove', function (e) {
            if (!isDown) return;
            e.preventDefault();
            var y = e.pageY - slider.offset().top;
            var walk = (y - startY) * 2;
            slider.scrollTop(scrollTop - walk);

            if (slider.scrollTop() >= slider.prop("scrollHeight") - slider.height()) {
                slider.scrollTop(0);
            }
        });
    });
    $(document).ready(function () {
        var slider = $('.slide2');
        var isDown = false;
        var startY;
        var scrollTop;

        slider.on('mousedown', function (e) {
            isDown = true;
            slider.addClass('active');
            startY = e.pageY - slider.offset().top;
            scrollTop = slider.scrollTop();
        });

        slider.on('mouseleave', function () {
            isDown = false;
            slider.removeClass('active');
        });

        slider.on('mouseup', function () {
            isDown = false;
            slider.removeClass('active');
        });

        slider.on('mousemove', function (e) {
            if (!isDown) return;
            e.preventDefault();
            var y = e.pageY - slider.offset().top;
            var walk = (y - startY) * 2;
            slider.scrollTop(scrollTop - walk);

            if (slider.scrollTop() >= slider.prop("scrollHeight") - slider.height()) {
                slider.scrollTop(0);
            }
        });
    });

    $(document).keydown(function (e) {
        // 현재 포커스된 요소가 입력 가능한 요소인지 확인
        var focusEl = document.activeElement;
        var isInput = focusEl.tagName === 'INPUT' || focusEl.tagName === 'TEXTAREA' || focusEl.isContentEditable;

        // 입력 필드가 아닐 때만 단축키 로직 실행
        if (!isInput) {
            if (e.altKey && e.key === "c") { // alt+c를 누르면 글 쓰기 페이지로 이동
                window.location.href = "<%=request.getContextPath()%>/board/write?bn=<%=postBoard%>";
            } else if (e.altKey && e.key === "w") { // alt+w를 누르면 새 글 페이지로 이동
                window.location.href = "<%=request.getContextPath()%>/board/<%=postBoard%>";
            } else if (e.key === "e") { // e를 누르면 상단으로 스크롤
                $("html, body").animate({scrollTop: 0}, 1);
            } else if (e.key === "d") { // d를 누르면 하단으로 스크롤
                $("html, body").animate({scrollTop: $(document).height()}, 1);
            } else if (e.key === "q") { // q를 누르면 메인 페이지로 이동
                window.location.href = "<%=request.getContextPath()%>/main";
            } else if (e.altKey && e.key === "1") { // 1을 누르면 영화 카테고리로 이동
                window.location.href = "<%=request.getContextPath()%>/main?cg=movie";
            }
        }
        // 추가적인 단축키 조합을 여기에 구현
    });

    //회원정보 보기 팝업창 열기 함수
    function openMemberInfoPopup(userId) {
        var url = "<%=request.getContextPath()%>/memberInfo?userId=" + userId;
        var options = "width=600,height=400,scrollbars=yes";
        window.open(url, "memberInfo", options);
    }


    function submitForm(event) {
        event.preventDefault(); // 폼 제출 기본 동작 막기

        var curPage = document.querySelector('input[name="curPage"]').value;

        if (curPage.trim() !== "" && parseInt(curPage) > 0) {
            // 숫자가 입력되었을 때만 페이지 이동
            var newUrl = window.location.pathname + '?curPage=' + curPage;
            window.location.href = newUrl;
        }
    }

</script>
<body>

<!-- 네비게이션바 -->
<jsp:include page="//common/navbar.jsp"></jsp:include>
<!-- <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container-fluid">
        로고
        <a class="navbar-brand" href="#">로고</a>

        토글 버튼
        <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        네비게이션 항목
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
                검색 바
                <form class="d-flex w-100">
                    <input class="form-control me-2 searchInput" type="search"
                        placeholder="검색" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">검색</button>
                </form>
            </ul>
            <ul class="navbar-nav">
                로그인, 마이페이지, 회원가입 버튼
                <li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
                <li class="nav-item"><a class="nav-link" href="#">마이페이지</a></li>
                <li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
            </ul>
        </div>
    </div>
</nav>-->


<div class="container mt-4">
    <div class="popular-boards-container">
        <div class="popular-board">
            <div class="popular-board-header">
                <h1>전체 인기글</h1>
            </div>
            <%
                List<PostPageDTO> plaList = (List<PostPageDTO>) request.getAttribute("popularListAll");
                for (int i = 0; i < plaList.size(); i++) {
                    PostPageDTO post = plaList.get(i);
            %>
            <div class="row">
                <div class="col-md-1"><%= i + 1 %>
                </div><!-- 순위  -->
                <div class="col-md-2"><%= post.getCategoryName() %>
                </div>
                <div class="col-md-7">
                    <a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=postBoard%>" class="post-title">
                        <%=post.getPostTitle()%>
                    </a>
                    <%
                        if (post.getCommentCount() != 0L) {
                    %>
                    &nbsp; <span class="comment-count"><%=post.getCommentCount()%></span>
                    <%
                        }
                    %>

                </div>
                <div class="col-md-2 text-center-align like-num"><%=post.getLikeNum()%>
                </div>
                <!-- 여기에 서버로부터 가져온 전체 인기글 목록을 반복하여 출력 -->
            </div>
            <% } %>
            <div class="popular-board-footer">
                <p>페이지 네비게이션</p>
            </div>
        </div>

        <div class="popular-board">
            <div class="popular-board-header">
                <h1><%= category %> 인기글</h1>
            </div>
            <%
                List<PostPageDTO> plcList = (List<PostPageDTO>) request.getAttribute("popularListCategory");
                for (int i = 0; i < plcList.size(); i++) {
                    PostPageDTO post = plcList.get(i);
                    String postCategory = post.getCategoryName();
            %>
            <div class="row">
                <div class="col-md-1"><%= i + 1 %>
                </div><!-- 순위  -->
                <div class="col-md-2"><%= postCategory %>
                </div>
                <div class="col-md-7">
                    <a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=postBoard%>" class="post-title">

                        <%=post.getPostTitle()%>
                    </a>
                    <%
                        if (post.getCommentCount() != 0L) {
                    %>
                    &nbsp; <span class="comment-count"><%=post.getCommentCount()%></span>
                    <%
                        }
                    %>

                </div>
                <div class="col-md-2 text-center-align like-num"><%=post.getLikeNum()%>
                </div>
                <!-- 여기에 서버로부터 가져온 전체 인기글 목록을 반복하여 출력 -->
            </div>
            <% } %>
            <div class="popular-board-footer">
                <p>페이지 네비게이션</p>
            </div>
        </div>
    </div>
</div>
<div class="container mt-4">
    <div class="row">
        <!-- 최신 개봉 영화 섹션 -->
        <div class="col-md-2">
            <h2 class="text-center new-hot-label">🌄신작 <%= category %> 랭킹🌄</h2>
            <div class="list-group slider-container slide1">
                <!-- 영화 포스터 반복 구간, 서버에서 가져온 최신 개봉 영화 데이터를 기반으로 반복 -->
                <%
                    MoviePoster poster = new MoviePoster(postBoard);
                    List<String> newMovieList = poster.getNewList();

                    for (int i = 0; i < newMovieList.size(); i++) { %>
                <div class="movie-slide">
                    <img src="<%= newMovieList.get(i) %>" alt="Movie Poster" class="img-fluid mb-2 slide-image">
                    <span class="index-label">
				      <%= i + 1 %>
			      </span>
                </div>
                <% } %>
            </div>
        </div>


        <div class="col-md-8">

            <!-- 게시글 목록 -->
            <div class="list-group">
                <div class="list-group-header d-flex justify-content-between align-items-center">


                    <h2 class="category-and-board-name">

                        <!-- 인라인 방식으로 요소 배치 -->

                        <div class="d-inline-flex align-items-center">
                            <i class="bg_color"></i>
                            <a class="font-black no-underline"
                               href="<%=request.getContextPath()%>/<%=link%>"><%=category%>
                            </a> <span>&nbsp;&gt;&nbsp;</span>
                            <!-- 드롭다운 메뉴로 변경 -->
                            <div class="dropdown">
							<span class="font-black no-underline dropdown-toggle"
                                  role="button" id="dropdownMenuLink" data-bs-toggle="dropdown"
                                  aria-expanded="false"> <%=boardName%>
							</span>
                                <!-- 드롭다운 메뉴 항목 -->
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                    <li><a class="dropdown-item"
                                           href="<%=request.getContextPath()%>/board/<%=boardType%>">
                                        🗣️자유 게시판</a></li>
                                    <li><a class="dropdown-item"
                                           href="<%=request.getContextPath()%>/board/<%=boardType%>Meet">
                                        🤝모임 게시판</a></li>
                                    <li><a class="dropdown-item"
                                           href="<%=request.getContextPath()%>/board/<%=boardType%>Info">
                                        📚정보 게시판</a></li>
                                </ul>
                            </div>
                        </div>
                    </h2>
                    <div class="d-flex justify-content-end">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                    id="dropdownMenuButton1" data-bs-toggle="dropdown"
                                    aria-expanded="false">정렬 옵션
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="javascript:void(0)"
                                       onclick="changeSort('likeNum')">추천순</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0)"
                                       onclick="changeSort('viewNum')">조회순</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0)"
                                       onclick="changeSort('regDate')">등록일순</a></li>
                            </ul>
                        </div>
                        &nbsp; <a href="/path/to/popular-posts" class="btn btn-success">
                        인기글 🔥 </a>

                    </div>
                </div>


                <div class="btn-group" role="group" aria-label="Category Tabs">
                    <a href="/acorn/board/<%=postBoard%>?pc=1<%
					if(request.getParameter("selectSearchPositionText") != null && request.getParameter("inputSearchFreeText") != null)
							{
						String sp = request.getParameter("selectSearchPositionText");
						String it = request.getParameter("inputSearchFreeText");
					
					%>&inputSearchFreeText=<%= it %>&selectSearchPositionText=<%= sp %>
					     <%
					     }; %>
					     " class="btn">일반</a>
                    <a href="/acorn/board/<%=postBoard%>?pc=2" class="btn">신작</a>
                    <a href="/acorn/board/<%=postBoard%>?pc=3" class="btn">후기</a>
                    <a href="/acorn/board/<%=postBoard%>?pc=4" class="btn">추천</a>
                    <a href="/acorn/board/<%=postBoard%>?pc=5" class="btn">토론</a>
                    <a href="/acorn/board/<%=postBoard%>?pc=6" class="btn">해외</a>
                </div>


                <!-- 테이블 헤더 -->
                <div class="list-group-item table-header margin-top">
                    <div class="row">
                        <div class="col-md-1 text-center-align">탭</div>
                        <div class="col-md-6 text-center-align">제목</div>
                        <div class="col-md-5 row">
                            <div class="col-md-4 text-center-align">글쓴이</div>
                            <div class="col-md-4 text-center-align">날짜</div>

                            <div class="col-md-2 text-center-align">
                                <a href="javascript:void(0)" onclick="toggleSort('viewNum')"
                                   class="font-black no-underline">조회</a>
                            </div>
                            <div class="col-md-2 text-center-align">
                                <a href="javascript:void(0)" onclick="toggleSort('likeNum')"
                                   class="font-black no-underline">추천</a>
                            </div>
                        </div>
                        <%-- <a href="/acorn/board/<%= postBoard %>?sortIndex=likeNum"> --%>
                    </div>
                </div>
                <!-- 인기글 출력부분 -->
                <div id="popularPostsSection" class="collapse show">
                    <%
                        if (hotList != null) {
                            for (PostPageDTO post : hotList) {
                                String displayDate = cdf.minuteHourDay(post);
                                String categoryName = post.getCategoryName();


                                String poscCategoryId = null;

                                switch (categoryName) {
                                    case "일반":
                                        poscCategoryId = "1";
                                        break;
                                    case "신작":
                                        poscCategoryId = "2";
                                        break;
                                    case "후기":
                                        poscCategoryId = "3";
                                        break;
                                    case "추천":
                                        poscCategoryId = "4";
                                        break;
                                    case "토론":
                                        poscCategoryId = "5";
                                        break;
                                    case "해외":
                                        poscCategoryId = "6";
                                        break;

                                }
                    %>
                    <div
                            class="list-group-item list-group-item-action"
                            style="background-color: #dff0d8;">
                        <div class="row">
                            <div class="col-md-1 text-center-align"><a
                                    href="/acorn/board/<%=postBoard%>?pc=<%=poscCategoryId %>"
                                    class="font-black no-underline"><%= categoryName %>
                            </a></div>
                            <div class="col-md-6">
                                <a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=postBoard%>"
                                   class="post-title">

                                    <%

                                        String postText = post.getPostText();
                                        Boolean videoExist = postText.contains("<video");
                                        Boolean imgExist = postText.contains("<img");


                                        if (imgExist && !videoExist) {
                                    %>
                                    <img src="/acorn/resources/images/picture.png" alt="description"
                                         class="post-thumbnail-picture">
                                    <%
                                            // 이미지 태그가 포함된 경우의 처리
                                        }
                                        if (videoExist) {%>
                                    <img src="/acorn/resources/images/video.png" alt="description"
                                         class="post-thumbnail-video">
                                    <%
                                        }
                                    %>

                                    <%=post.getPostTitle()%>
                                </a>
                                <% if (post.getCommentCount() != 0L) {%>
                                &nbsp;
                                <span class="comment-count"><%=post.getCommentCount()%></span>
                                &nbsp;
                                <% } %>
                                <span style="color: red">hot🔥</span>
                            </div>
                            <div class="col-md-5 row">
                                <div class="col-md-4 text-center-align">
                                    <div class="dropdown">
                                        <a href="#" class="dropdown-toggle no-underline font-black"
                                           data-bs-toggle="dropdown" aria-expanded="false">
                                            <%=post.getNickname()%>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item"
                                                   href="<%= request.getContextPath() %>/board/<%= postBoard %>?selectSearchPositionText=userId&inputSearchFreeText=<%=post.getUserId()%>">📑작성글
                                                보기</a></li>
                                            <li><a class="dropdown-item" href="#"
                                                   onclick="openMemberInfoPopup('<%=post.getUserId()%>'); return false;">🔎회원정보
                                                보기</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <%
                                    String strPostDate = sdfDate.format(post.getPostDate());
                                    String formattedDate;
                                    if (strToday.equals(strPostDate)) {
                                        formattedDate = new SimpleDateFormat("HH:mm").format(post.getPostDate());
                                    } else {
                                        formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(post.getPostDate());
                                    }
                                %>
                                <div class="col-md-4 text-center-align"><%=formattedDate%>
                                </div>
                                <div class="col-md-2 text-center-align"><%=post.getViewNum()%>
                                </div>
                                <% if (post.getLikeNum() != 0L) { %>
                                <div class="col-md-2 text-center-align like-num"><%=post.getLikeNum()%>
                                </div>
                                <%} %>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    인기글이 존재하지 않습니다.
                    <%
                        }
                    %>
                </div>
                <!-- 섹션 접기 버튼 -->
                <div class="text-center">
                    <button class="btn btn-link btn-sm" type="button"
                            data-bs-toggle="collapse" data-bs-target="#popularPostsSection"
                            aria-expanded="true" aria-controls="popularPostsSection">
                        <span class='no-underline font-black'>▲</span>
                    </button>
                </div>


                <%
                    for (PostPageDTO post : list) {
                        String categoryName = post.getCategoryName();
                        String poscCategoryId = null;

                        switch (categoryName) {
                            case "일반":
                                poscCategoryId = "1";
                                break;
                            case "신작":
                                poscCategoryId = "2";
                                break;
                            case "후기":
                                poscCategoryId = "3";
                                break;
                            case "추천":
                                poscCategoryId = "4";
                                break;
                            case "토론":
                                poscCategoryId = "5";
                                break;
                            case "해외":
                                poscCategoryId = "6";
                                break;

                        }

                %>
                <div class="list-group-item list-group-item-action">
                    <div class="row">
                        <div class="col-md-1 text-center-align"><a
                                href="/acorn/board/<%=postBoard%>?pc=<%=poscCategoryId %>"
                                class="font-black no-underline"><%= categoryName %>
                        </a></div>
                        <div class="col-md-6">
                            <a href="/acorn/board/content?postId=<%=post.getPostId()%>&bn=<%=postBoard%>"
                               class="post-title">
                                <%

                                    String postText = post.getPostText();
                                    Boolean videoExist = postText.contains("<video");
                                    Boolean imgExist = postText.contains("<img");


                                    if (imgExist && !videoExist) {
                                %>
                                <img src="/acorn/resources/images/picture.png" alt="description"
                                     class="post-thumbnail-picture">
                                <%
                                        // 이미지 태그가 포함된 경우의 처리
                                    }
                                    if (videoExist) {%>
                                <img src="/acorn/resources/images/video.png" alt="description" class="post-thumbnail-video">
                                <%
                                    }
                                %>

                                <%=post.getPostTitle()%>
                            </a>
                            <% if (post.getCommentCount() != 0L) {%>
                            &nbsp;
                            <span class="comment-count"><%=post.getCommentCount()%></span>
                            <% } %>
                        </div>
                        <div class="col-md-5 row">
                            <div class="col-md-4 text-center-align">
                                <div class="dropdown">
                                    <a href="#" class="dropdown-toggle no-underline font-black"
                                       data-bs-toggle="dropdown" aria-expanded="false">
                                        <%=post.getNickname()%>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item"
                                               href="<%= request.getContextPath() %>/board/<%= postBoard %>?selectSearchPositionText=userId&inputSearchFreeText=<%=post.getUserId()%>">📑작성글
                                            보기</a></li>
                                        <li><a class="dropdown-item" href="#"
                                               onclick="openMemberInfoPopup('<%=post.getUserId()%>'); return false;">🔎회원정보
                                            보기</a></li>
                                    </ul>
                                </div>
                            </div>
                            <%
                                String strPostDate = sdfDate.format(post.getPostDate());
                                String formattedDate;
                                if (strToday.equals(strPostDate)) {
                                    formattedDate = new SimpleDateFormat("HH:mm").format(post.getPostDate());
                                } else {
                                    formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(post.getPostDate());
                                }
                            %>
                            <div class="col-md-4 text-center-align"><%=formattedDate%>
                            </div>
                            <div class="col-md-2 text-center-align"><%=post.getViewNum()%>
                            </div>
                            <% if (post.getLikeNum() != 0L) { %>
                            <div class="col-md-2 text-center-align like-num"><%=post.getLikeNum()%>
                            </div>
                            <%} %>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>


                <div class="mb-3 search-write-group margin-top">
                    <!-- 검색창 -->
                    <div class="search-input-group">
                        <form id="formSearchFree" action="/acorn/board/<%=postBoard%>"
                              class="d-flex">
                            <div class="col-md-4 pe-1">
                                <select class="form-select search-form-control"
                                        name="selectSearchPositionText">
                                    <option value="titleText">제목 + 내용</option>
                                    <option value="postTitle">제목</option>
                                    <option value="postText">내용</option>
                                    <option value="userId">작성자</option>
                                </select>
                            </div>
                            <div class="col-md-6 pe-1">
                                <input type="text" class="form-control search-form-control"
                                       id="inputSearchFreeText" name="inputSearchFreeText">
                            </div>
                            <div class="col-md-2">
                                <button class="btn search-button" type="submit">검색</button>
                            </div>
                        </form>
                    </div>


                    <!-- 글쓰기 버튼 -->
                    <div>
                        <a href="/acorn/board/write?bn=<%=postBoard%>">
                            <button type="button" class="btn write-button custom-btn">글쓰기</button>
                        </a>
                    </div>
                </div>


                <!-- 페이징 로직 -->
                <%
                    int curPage = pDTO.getCurPage();
                    int perPage = pDTO.getPerPage();
                    int totalCount = pDTO.getTotalCount();
                    int totalPage = (int) Math.ceil((double) totalCount / perPage);
                    int startPage = ((curPage - 1) / 10) * 10 + 1; // 시작 페이지 번호 계산
                    int endPage = Math.min(startPage + 9, totalPage); // 끝 페이지 번호 계산

                    // Calculate previous and next page numbers
                    int prevPage = Math.max(startPage - 1, 1); // Ensure prevPage is never less than 1
                    int nextPage = endPage + 1;
                %>

                <div class="page-numbers text-center">
                    <ul class="pagination">

                        <%-- "이전" 버튼 --%>
                        <%
                            if (curPage > 1) {
                        %>
                        <li class="page-item"><a class="page-link"
                                                 href="/acorn/board/<%=postBoard%>?curPage=<%=prevPage%><%if (sortIndex != null) {%>&sortIndex=<%=sortIndex%><%}%><%if (inputSearchFreeText != null && selectSearchPositionText != null) {%>&selectSearchPositionText=<%=selectSearchPositionText%>&inputSearchFreeText=<%=inputSearchFreeText%><%}%>">
                            &laquo; 이전 </a></li>
                        <%
                        } else {
                        %>
                        <li class="page-item disabled"><span class="page-link">&laquo;
									이전</span></li>
                        <%
                            }
                        %>

                        <%-- 페이지 번호 출력 --%>
                        <%
                            for (int i = startPage; i <= endPage; i++) {
                        %>
                        <li class="page-item <%=i == curPage ? "active" : ""%>"><a
                                class="page-link"
                                href="/acorn/board/<%=postBoard%>?curPage=<%=i%><%if (sortIndex != null) {%>&sortIndex=<%=sortIndex%><%}%><%if (inputSearchFreeText != null && selectSearchPositionText != null) {%>&selectSearchPositionText=<%=selectSearchPositionText%>&inputSearchFreeText=<%=inputSearchFreeText%><%}%>">
                            <%=i%>
                        </a></li>
                        <%
                            }
                        %>

                        <%-- "다음" 버튼 --%>
                        <%
                            if (nextPage <= totalPage) {
                        %>
                        <li class="page-item"><a class="page-link"
                                                 href="/acorn/board/<%=postBoard%>?curPage=<%=nextPage%><%if (sortIndex != null) {%>&sortIndex=<%=sortIndex%><%}%><%if (inputSearchFreeText != null && selectSearchPositionText != null) {%>&selectSearchPositionText=<%=selectSearchPositionText%>&inputSearchFreeText=<%=inputSearchFreeText%><%}%>">
                            다음 &raquo; </a></li>
                        <%
                        } else {
                        %>
                        <li class="page-item disabled"><span class="page-link">다음
									&raquo;</span></li>
                        <%
                            }
                        %>
                    </ul>
                    <!-- 페이지 숫자 검색창 -->
                    <form action="" method="get" onsubmit="submitForm(event)">
                        <input type="number" name="curPage" style="width: 50px">
                    </form>
                </div>
            </div>
        </div>
        <!-- 인기 영화 섹션 -->
        <div class="col-md-2">
            <h2 class="text-center new-hot-label">💥전체 <%= category %> 랭킹💥</h2>
            <!-- 인기 영화 목록을 여기에 -->
            <div class="list-group slider-container slide2">
                <!-- 영화 포스터 반복 구간 -->
                <%-- 서버에서 가져온 최신 개봉 영화 데이터를 기반으로 반복 --%>
                <%
                    List<String> hotMovieList = poster.getHotList();

                    for (int i = 0; i < hotMovieList.size(); i++) {
                %>
                <div class="movie-slide">
                    <img src="<%= hotMovieList.get(i) %>" alt="Movie Poster" class="img-fluid mb-2 slide-image">
                    <span class="index-label"><%= i + 1 %></span>
                </div>

                <%
                    }
                %>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="shortcut-container">
            <div class="shortcut-list">
                <span class="shortcut-key">alt+c</span><span class="shortcut-description">글 쓰기</span>
                <span class="shortcut-key">alt+w</span><span class="shortcut-description">새 글</span>
                <span class="shortcut-key2">e</span><span class="shortcut-description">상단으로</span>
                <span class="shortcut-key2">d</span><span class="shortcut-description">하단으로</span>
                <span class="shortcut-key2">s</span><span class="shortcut-description">이전</span>
                <span class="shortcut-key">1</span><span class="shortcut-description">영화</span>
            </div>
        </div>
    </div>

</div>


<footer class="site-footer">
    <div class="footer-content">
        <ul class="footer-links">
            <li><a href="#">소개</a></li>
            <li><a href="#">이용약관</a></li>
            <li><a href="#">개인정보처리방침</a></li>
            <li><a href="#">청소년 보호정책</a></li>
            <li><a href="#">문의/신고</a></li>
            <li><a href="#">문제보고</a></li>
        </ul>
        <p class="footer-contact">문의메일 : <a href="mailto:admin@moonbam.net">admin@moonbam.net</a></p>
        <p class="copyright">©moonbam All rights reserved.</p>
    </div>
</footer>


<!-- Bootstrap Bundle with Popper -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">

</script>
<script>
    // 현재 페이지 URL에서 sortIndex 파라미터 값을 가져오는 함수
    function getCurrentSortIndex() {
        var params = new URLSearchParams(window.location.search);
        return params.get("sortIndex");
    };

    // 정렬 링크를 클릭했을 때의 동작을 정의하는 함수
    function toggleSort(sortType) {
        var currentSortIndex = getCurrentSortIndex();

        // URL 생성
        var url = "/acorn/board/<%=postBoard%>?curPage=<%=curPage%>";

        // 첫 번째 클릭시 sortIndex 추가, 두 번째 클릭시 sortIndex 제거
        if (currentSortIndex !== sortType) {
            url += "&sortIndex=" + sortType;
        }

        // 페이지 로드
        window.location.href = url;
    };

    // 정렬 옵션 변경 함수
    function changeSort(sortType) {
        var currentUrl = window.location.href;
        var newUrl = new URL(currentUrl);
        newUrl.searchParams.set('sortIndex', sortType);
        newUrl.searchParams.set('curPage', 1); // 정렬 기준 변경 시 첫 페이지로 리셋
        window.location.href = newUrl.toString();
    };


</script>


</body>
</html>
