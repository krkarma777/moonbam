<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>

    .text-pink {
        color: #ff416c; /* Bright pink text for emphasis */
    }

    .border-pink {
        border-color: #ff416c !important; /* Bright pink border */
    }

    .btn-pink {
        background-color: #ff416c;
        color: white;
    }

    .btn-pink:hover {
        background-color: #ff6392;
        color: white;
    }

    table {
        background-color: white;
    }
</style>

<c:if test="${!empty mesg }">
    <script>alert("${mesg}")</script>
</c:if>

<div class="container">
    <h1 class="text-center text-pink">신고된 게시글 관리</h1>
    <hr>

    <form action="<%=request.getContextPath() %>/AdminPage/AdminPostReported" method="post" class="my-4">
        <div class="input-group mb-3">
            <select name="criteria" class="form-select border-pink">
                <option value="POSTID" selected>글 번호</option>
                <option value="USERID">신고대상</option>
                <option value="REPORTER">신고자</option>
            </select>
            <input type="text" class="form-control border-pink" placeholder="검색조건 입력" name="SearchValue">
            <button class="btn btn-pink" type="submit">검색</button>
        </div>
    </form>

    <table class="table table-hover">
        <thead>
        <tr class="bg-pink text-white">
            <th>선택</th>
            <th>신고번호</th>
            <th>글 번호</th>
            <th>신고자</th>
            <th>작성자</th>
            <th>음란물</th>
            <th>언어</th>
            <th>도배</th>
            <th>규정위반</th>
            <th>기타</th>
            <th>신고내용</th>
            <th>처리내용</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dto" items="${list}">
            <tr>
                <td><input type="checkbox" class="chkPost" data-xxx="${dto.targetId}"></td>
                <td>${fn:indexOf(list, dto)+1}</td> <!-- Displaying index as report number -->
                <td><a href="#" class="showContent text-pink">${dto.targetId}</a></td>
                <td>${dto.reporterId}</td>
                <td>${dto.userId}</td>
                <td>${dto.sexual}</td>
                <td>${dto.lang}</td>
                <td>${dto.abusing}</td>
                <td>${dto.ruleviolation}</td>
                <td>${dto.etc}</td>
                <td>${dto.cont}</td>
                <td>${dto.action}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button type="button" id="chkAll" class="btn btn-pink">모두 선택/해제</button>
    <button type="button" id="delChecked" class="btn btn-pink">삭제</button>
</div>

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
