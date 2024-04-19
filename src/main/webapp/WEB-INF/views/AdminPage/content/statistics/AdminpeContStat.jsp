<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- CDN -->
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

<body>
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
	      backgroundColor:"rgba(0,0,0,0)",
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


관리자 통계확인 페이지
<hr>
접속자 통계
<br>

<!-- 그래프 -->
<canvas id="myChart" style="width:40%;max-width:700px"></canvas>
<br>

<hr>
미처리신고<br>
신고된 게시글/댓글<br>
영리 개인정보침해 불법 음란 폭언 도배 이용규칙위반<br>
<br>
신고된 사용자<br>
영리 개인정보침해 불법 음란 폭언 도배 이용규칙위반


</body>

</html>