<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<div class="container">
    <!-- chart data -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const xValues = [1,2,3,4,5,6,7,8,9,10,11];
            const yValues = [7,8,8,9,9,9,10,11,14,14,15];


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
        <p>현재 접속자 수: <strong>XX명</strong></p>
    </div>
    <hr>
    <h2>미처리 신고</h2>
    <p>신고된 게시글/댓글</p>
    <p>영리, 개인정보침해, 불법, 음란, 폭언, 도배, 이용규칙위반</p>
    <p>신고된 사용자</p>
    <p>영리, 개인정보침해, 불법, 음란, 폭언, 도배, 이용규칙위반</p>
</div>
</body>
</html>
