<%@ page import="java.util.*" %>
<%@ page import="com.moonBam.dto.AdminReportDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>
    form {
        width: 70%;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    select, input[type="text"], input[type="submit"] {
        width: calc(100% - 100px);
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

    .center {
        text-align: center;
    }
</style>
<h1>신고된 댓글 관리</h1>
<form action="<%=request.getContextPath() %>/AdminPage/AdminPostDeleted">
    <select name="SearchCondition">
        <option value="" class="SearchStandard">글 아이디</option>
        <option value="" class="SearchStandard">글 제목</option>
        <option value="" class="SearchStandard">작성자</option>
        <option value="" class="SearchStandard">삭제사유</option>
        <option value="" class="SearchStandard">처분여부</option>
        <option value="" class="SearchStandard">완전삭제예정일</option>
    </select>
    <input type="text" placeholder="검색조건 입력" id="SearchValue" name="SearchValue">
    <input type="submit" value="검색">
    <hr>
    <table border="1">
        <tr>
            <th>댓글번호</th>
            <th>작성자</th>
            <th>성적인 댓글</th>
            <th>언어</th>
            <th>도배</th>
            <th>규정위반</th>
            <th>기타</th>
            <th>처분</th>
        </tr>
            <!-- <td colspan="8" class="center">검색조건을 입력하세요.</td> -->
        <tr>
            <td></td>
            <td><a href="#"></a></td>
            <td><a href="#"></a></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><button class="DelBtn">삭제</button></td>
        </tr>
    </table>
</form>
