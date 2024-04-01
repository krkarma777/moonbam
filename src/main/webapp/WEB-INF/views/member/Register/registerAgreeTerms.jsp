<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<!-- 회원가입을 위한 약관에 동의하는 페이지.jsp -->

<head>
    <meta charset="UTF-8">
    <title>약관 동의 페이지</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/member/register_term.css'/>">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

    <div class="container">
    
        <h1>약관 동의 페이지</h1>

        <form id="agreementForm" action="<c:url value='/CheckExistUser'/>" method="post">
		
			<input type="hidden" name="email" value="${email}">

            <div>
                <textarea readonly="readonly">
                    이용 약관 내용
                </textarea>
                <label><input type="checkbox" class="terms" name="checked_Agreement">이용 약관에 동의합니다.</label>
            </div>

            <div>
                <textarea readonly="readonly">
                    개인정보 처리방침 내용
                </textarea>
                <label><input type="checkbox" class="terms" name="checked_Info">개인정보 처리방침에 동의합니다.</label>
            </div>

            <div>
                <textarea readonly="readonly">
                    회원 탈퇴 및 서비스 이용 중지 규정 내용
                </textarea>
                <label><input type="checkbox" class="terms" name="checked_Withdraw">회원 탈퇴 및 서비스 이용 중지에 동의합니다.</label>
            </div>
            <br>

            <div>
                <label><input type="checkbox" id="allCheckbox" onclick="clickAllChk(this.checked)">모두 동의합니다.</label>
            </div>
            <button type="button" onclick="chkAgreement()">다음 페이지로 이동</button>
        </form>
    </div>

    <script type="text/javascript">
        $(function() {
        	//모두 동의를 클릭하면 clickAllChk함수 발동(다른 체크박스가 모두 체크)
            $("#allCheckbox").click(function() {
                clickAllChk(this.checked);
            });

         	// 개별 약관에 대한 체크박스 클릭 시 모두 동의 체크박스 상태 갱신
            $(".terms").click(function() {
                $("#allCheckbox").prop("checked", $(".terms:checked").length === $(".terms").length);
            });
        });

    	//모두 동의를 클릭하면 clickAllChk함수 발동(다른 체크박스가 모두 체크)
        function clickAllChk(tc) {
            $(".terms").prop("checked", tc);
        }

    	//모두 동의를 제외한 모든 체크박스가 체크되지 않으면 다음 페이지로 이동 불가
        function chkAgreement() {
            if ($(".terms:not(:checked)").length === 0) {
                alert("약관에 모두 동의하셨습니다. 다음 페이지로 이동합니다.");
                $("#agreementForm").submit();
            } else {
                alert("모든 약관에 동의해야 다음 페이지로 이동할 수 있습니다.");
            }
        }
    </script>

</body>
</html>
