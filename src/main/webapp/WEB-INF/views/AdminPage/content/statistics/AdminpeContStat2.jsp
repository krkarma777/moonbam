<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Google Charts -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Activity', 'Count'],
            ['Reviews', 8],
            ['Posts', 12],
            ['Comments', 17]
        ]);

        var options = {
            title: 'Today\'s Activity',
            is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
</script>
<style>
    .container {
        width: 80%;
        overflow: hidden;
        padding: 20px;
        background: white;
        border-radius: 8px;
    }
    hr {
        border: none;
        height: 1px;
        background: silver;
        margin-top: 20px;
        margin-bottom: 20px;
    }
</style>
<div class="container">
<h1>관리자 통계 확인 페이지</h1>
<hr>
<div id="piechart" style="width: 900px; height: 500px; margin: auto;"></div>
<hr>
<h2>오늘 작성된 게시물</h2>
<p>리뷰: 8건</p>
<p>게시판: 12건</p>
<p>댓글: 17건</p>
</div>
