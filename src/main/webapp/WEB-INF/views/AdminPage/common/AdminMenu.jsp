<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<form action = "#">

<a href="#">통계</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toStatistics">----접속자 통계</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toStatistics2">----컨텐츠 통계</a><br>

<a href="#">게시물 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/AdminPostReported">----신고글 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/AdminPagePostRule">----글 작성 규칙 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/AdminPageDeletedPost">----삭제된 게시글 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/AdminPageDeletedComment">----삭제된 댓글 관리</a><br>

<a href="#">회원 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageMemRprtedMem">----신고회원관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageMemGrade">----회원등급관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageMemRule">----등급관리규칙설정</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageMemRestricted">----이용제한 회원 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageDeletedMember">----삭제된 회원 데이터 관리</a><br>

<a href="#">공지 관리</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageAnnounce">----공지글</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageEvent">----이벤트</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageAPI">API</a><br>
<a href="<%=request.getContextPath() %>/AdminPage/toAdminPageMonitoring">모니터링</a>

</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">
	
		
			
		
		
		
	
</script>