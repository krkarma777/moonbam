<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

<div id="header">
    <jsp:include page="/WEB-INF/views/common/navBar.jsp" flush="true"></jsp:include><br>
</div>

    <div class="container mt-5">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title">문화인들의 밤에 복귀하시겠습니까?</h3>
                <form action="<c:url value='/checkRestoreMember'/>" method="post">
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="submit" name="yesBtn" class="btn" value="예">
                    <input type="submit" name="noBtn" class="btn" value="아니오">
                </form>
            </div>
        </div>
    </div>

    <script type="text/javascript">

	  	//새로고침, 뒤로가기, 나가기 시 경고창 함수
		function f5Control(event){
			event.preventDefault();
		    event.returnValue = '';
		}
    
    	$(function(){
    		
			//새로고침, 뒤로가기, 나가기 시 경고창 함수 출력
			window.addEventListener('beforeunload', f5Control);
			
			//뒤로가기 단축키을 누르면 로그인 메인으로 이동(Alt + <- 기능)(브라우저 뒤로가기 버튼은 막히지 않음)
			window.history.pushState(null, null, window.location.href);
			window.onpopstate = function(event) {
				window.history.pushState(null, null, window.location.href);
				window.location.href= "<c:url value='/'/>"; 
			};
   		    
			$(".btn").on("click", function(){
				window.removeEventListener('beforeunload', f5Control);
	 		})
        })
    </script>

    <script src="https://fastly.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
