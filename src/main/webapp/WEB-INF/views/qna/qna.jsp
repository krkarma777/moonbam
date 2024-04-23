<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.QnADTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문화인들의 밤</title>
</head>
<body>
	<%
	List<QnADTO> list = (List<QnADTO>)request.getAttribute("list");
	System.out.println("in jsp");
	System.out.println(list);
	%>
    <h1>내 문의</h1>
    <br>
    <hr>
    	<!-- 리스트 출력 -->
	<table border = "1">
		<tr>
            <th>QnA ID</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>내용</th>
            <th>답변</th>
            <th>답변일</th>
            <th>조치</th>
            
        </tr>
		<%
		
		/* 리스트 없으면 검색조건 입력하시오  */
		
		if (list ==null){
		%>
		<tr>
		<td colspan = "6">문의가 없습니다.</td>
		</tr>
		<%
		}else{
			/* 리스트 있으면 출력 */
			for(QnADTO dto : list){
		%>
				<tr>
					
					<td><%=dto.getQnaid() %></td>
					<td><%=dto.getCat() %></td>
					<td><a href=""><%=dto.getTitle() %></a></td>
					<td><%=dto.getText() %></td>
					<td><%=dto.getAnswer() %></td>
					<td><%=dto.getAnswerdate() %></td>
					<td><input type = "button" value = "수정"><input type = "button" value = "삭제"></td>
				</tr>
			<%} %>
		<%} %>	
	</table>
</form>
    
    <button onclick="goBack()">back</button><button onclick="toNewQNA()">new qna</button>

    <script type="text/javascript">
        
        function toNewQNA(){
            window.location.href = "<%=request.getContextPath()%>/newQNA";
        }
        
        function goBack() {
            window.history.back();
        }
        
    </script>
</body>
</html>
