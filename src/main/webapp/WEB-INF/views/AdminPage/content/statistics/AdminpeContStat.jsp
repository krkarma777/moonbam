<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="java.util.List" %>
<%@ page import="com.moonBam.dto.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>문화인들의 밤</title>
    <!-- CDN -->
    <script src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <!-- Style -->
    <style>

        .container {
            width: 80%;
            overflow: hidden;
            padding: 20px;
            background: white;
            border-radius: 8px;
        }
        .chart-container {
            background: #ffffff;
            padding: 15px;
        }
        hr {
            border: none;
            height: 1px;
            background: silver;
            margin-top: 20px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<script>
 var xValues = [];
 var yValues = [];
 
</script>


<c:forEach var="item" items="${list1}">
    <script>
        var data = "${item}"; // 데이터를 JavaScript 변수로 받아옴
        var splitedData = data.split(/[\[\],;\s-=]+/);
        console.log(splitedData);
        
        xValues.push(splitedData[4]);
        yValues.push(splitedData[9]);
    </script>
</c:forEach>


<div class="container">
    <!-- chart data -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            console.log(xValues);
            console.log(yValues);

            new Chart("myChart", {
                type: "line",
                data: {
                    labels: xValues,
                    datasets: [{
                        backgroundColor: "rgba(0,0,0,0)",
                        borderColor: "rgba(0,0,255,0.5)",
                        data: yValues
                    }]
                },
                options: {
                    legend: {display: false},
                    scales: {
                        yAxes: [{ticks: {min: 0, max:30}}],
                    }
                }
            });//chart
        });
    </script>

    <h1>관리자 통계 확인 페이지</h1>
    <hr>
    <div class="chart-container">
        <h2>접속자 통계</h2>
        <!-- 그래프 -->
        <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
        <!-- <p>현재 접속자 수: <strong>XX명</strong></p> -->
    </div>
    <hr>
    <h2>미처리 신고</h2>
    <p>신고된 게시글/댓글</p>
    <p>
    음란물 ${rDTO.sexual }건 , 
    언어규정위반 ${rDTO.lang }건, 
    도배 ${rDTO.abusing }건, 
    규정위반 ${rDTO.ruleviolation }건, 
    기타 ${rDTO.etc }건
    </p>
</div>
</body>
</html>