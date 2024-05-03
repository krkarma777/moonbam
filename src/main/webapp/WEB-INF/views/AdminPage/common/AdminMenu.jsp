<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container mt-4">
    <div class="row justify-content-center admin-menu">
        <!-- Statistics Management Dropdown -->
        <div class="col-auto">
            <select class="form-select" onchange="location = this.value;">
                <option selected>통계 관리</option>
                <option value="<%=request.getContextPath()%>/AdminPage/toStatistics">접속자 통계</option>
            </select>
        </div>

        <!-- Posts Management Dropdown -->
        <div class="col-auto">
            <select class="form-select" onchange="location = this.value;">
                <option selected>게시물 관리</option>
                <option value="<%=request.getContextPath()%>/AdminPage/AdminPostReported">신고글 관리</option>
                <%-- <option value="<%=request.getContextPath()%>/AdminPage/AdminPagePostRule">글 작성 규칙 관리</option> --%>
                <option value="<%=request.getContextPath()%>/AdminPage/AdminPageDeletedPost">삭제된 게시글 관리</option>
                <%-- <option value="<%=request.getContextPath()%>/AdminPage/AdminPageReportedComment">신고된 댓글 관리</option> --%>
            </select>
        </div>

        <!-- Members Management Dropdown -->
        <div class="col-auto">
            <select class="form-select" onchange="location = this.value;">
                <option selected>회원 관리</option>
                <option value="<%=request.getContextPath()%>/AdminPage/toAdminPageMemRprtedMem">신고회원 관리</option>
                <%-- <option value="<%=request.getContextPath()%>/AdminPage/toAdminPageMemRule">등급 관리 규칙 설정</option> --%>
                <option value="<%=request.getContextPath()%>/AdminPage/toAdminPageMemRestricted">이용 제한 회원 관리</option>
                <option value="<%=request.getContextPath()%>/AdminPage/toAdminPageDeletedMember">삭제된 회원 데이터 관리</option>
            </select>
        </div>
        <!-- Notices and Other Management Dropdown -->
        <div class="col-auto">
            <select class="form-select" onchange="location = this.value;">
                <option selected>공지 및 기타 관리</option>
                <option value="<%=request.getContextPath()%>/AdminPage/toAdminPageAnnounce">공지글</option>
            </select>
        </div>
    </div>
</div>

