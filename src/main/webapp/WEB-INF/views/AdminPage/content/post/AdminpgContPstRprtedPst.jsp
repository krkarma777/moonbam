<%@ page import = "java.util.*" %>
<%@ page import = "com.moonBam.dto.AdminRprtdDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test = "${!empty mesg }" >
	<script>alert("${mesg}")</script>
</c:if>

<script type = text/javascript>
	
</script>

<%
List<AdminRprtdDTO>list = (List<AdminRprtdDTO>)request.getAttribute("list");
System.out.println("in jsp");
System.out.println(list);
String url = request.getContextPath();
%>

</head>
<body>
<h1>신고된 게시글</h1>
<hr>
<form action = "<%=request.getContextPath() %>/AdminPage/AdminPostReported">
	<select name = "criteria">
		<option value = "POSTID" class = "criteria" selected>글 번호</option>
		<option value = "USERID" class = "criteria">신고대상</option>
		<option value = "REPORTER" class = "criteria">신고자</option>
	</select>
	
	<input type = "text" placeholder = "검색조건 입력"  id = "SearchValue" name = "SearchValue">
	<input type="submit" value = "검색"> 
	
	<hr>
	<table border = "1" >
		<tr>
			<th rowspan="2" colspan = "2">신고번호</th>
			<th rowspan="2">신고된 글 번호</th>
			<th rowspan="2">신고자</th>
			<th rowspan="2">작성자</th>
			<th colspan="5">신고사유</th>
			<th rowspan="2">신고내용</th>
			<th rowspan="2">처리내용</th>
		</tr>
		<tr>
			<th>음란물</th>
			<th>언어</th>
			<th>도배</th>
			<th>규정위반</th>
			<th>기타</th>
		</tr>
		 <%
		if (list == null){
		%>
			<tr>
				<td colspan = "11" align = "center">검색조건을 입력하세요.</td>
			</tr> 
			<%}else{ 
			for(AdminRprtdDTO dto : list){
			%>
					<tr>
						<td><%=dto.getReportid() %></td>
						<td><input type = "checkbox" class = "chkPost" data-xxx = <%=dto.getPostid() %>></td>
						<td><a href = "#" class = "showContent"><%=dto.getPostid() %></a></td>
						<td><%=dto.getReporter() %></td>
						<td><%=dto.getUserid() %></td>
						<td><%=dto.getSexual() %></td>
						<td><%=dto.getLang() %></td>
						<td><%=dto.getAbusing() %></td>
						<td><%=dto.getRuleviolation() %></td>
						<td><%=dto.getEtc() %></td>
						<td><%=dto.getCont() %></td>
						<td><%=dto.getAction() %></td>
					</tr>
			<%}} %>

	</table>
</form>
<input type = "button" id = "chkAll" name = "chkAll" value = "모두 선택/해제">
<input type = "button" id = "delChecked" name = "delChecked" value = "삭제">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">

	$(document).ready(function(){
		
		$("#chkAll").on("click", chkArr);
		$("#delChecked").on("click", delChecked);
		$(".showContent").on("click", showContent);
		
		var flag = 1;
		function chkArr(){
			//console.log("chkArr");
			if (flag==1){
				$(".chkPost").attr("checked", true);
				flag = 0;
			}else{
				$(".chkPost").attr("checked", false);
				flag = 1;
			}
		}//chkArr
		
		
		function delChecked(){
			
			//디버깅용 확인
			console.log("chkArr");
			//삭제할 글id저장용 배열
			let dupPostArr = [];
			//체크된 글 id를 배열에 저장
			$(".chkPost:checked").each(function(i,e){
				dupPostArr.push($(this).attr("data-xxx"));
			});//each
			//확인
			console.log("dupPostArr: "+dupPostArr);
			
			//중복제거
			var postArr = [];
			for(postid of dupPostArr){
				if(!postArr.includes(postid)){
					postArr.push(postid);
				}	
			}
			
			//확인
			console.log("postArr: " + postArr);
	
			//전송
			var target = "<%=request.getContextPath()%>" + "/AdminPage/DeletePost?postArr="+postArr;
			console.log(target);
			location.href = "<%=request.getContextPath()%>" + "/AdminPage/DeletePost?postArr="+postArr;
		}//delChecked
		
		//해당 글 아이디 클릭시 자식창으로 글 내용 확인
		function showContent(){
			//글 아이디 파싱 확인
			var postid = $(this).text()
			console.log(postid);
			
			// 글 내용 보여줄 자식창을 변수에 저장
			
			
			//ajax로 글 내용 받아서 자식창으로 전달
			$.ajax({
				type: "get",
				url:"AdminShowPostContentServlet",
				data:{postid:postid},
				dataType:"json",
				success:function(data, status, xhr){
					
					//childWin;
					
				},//success
				error:function(xhr, status, error){
					console.log(error);
				}
			})//ajax
			
			
			
		}//showContent
		
	});//document
</script>
</body>
</html>