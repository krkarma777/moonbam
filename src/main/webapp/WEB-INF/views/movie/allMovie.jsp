<%@page import="com.moonBam.dto.ContentDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.moonBam.dto.MoviePageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	MoviePageDTO mpDTO = (MoviePageDTO)request.getAttribute("mpDTO");
	List<ContentDTO> movieList = mpDTO.getList();
	System.out.print(movieList.size());
%>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style type="text/css">
.innerImage {
	float: right;
}
.poster{
	position: relative;
	width: 240px;
}

.title{
	position: absolute;
	top: 238px;
	left: 2px;
 	padding: 0px 3px;
    border-radius: 5px;
    font-size: 30px;
    text-shadow: 3px 3px 6px rgba(0, 0, 0, 1);
    color: #ffb2c4;
    font-style: italic;
    /*   -webkit-text-stroke: 1px #fff; */
    font-family: 'TheJamsil5Bold';
}

@font-face {
	font-family: 'TheJamsil5Bold';
	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
	font-weight: 700;
	font-style: normal;
}
.pagination{
	justify-content: center;
}
.pagination .page-link{
	border: none;
	color:white; 
	background-color: #ff416c; 
	opacity:0.8;
	color: white;
}
.pagination .page-item.active .page-link{
	color:white; 
	background-color: #ff416c;
	opacity:0.8; 
	color: white;
	border: none;
}
/* 활성화된 페이지 번호 스타일 */
.pagination .page-item.active .page-link {
    background-color: #FF285A;
    background-image: linear-gradient(180deg, #FF285A, #FF174D);
    /* 그라디언트 효과 */
    border: none; /* 테두리 제거 */
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body class="bg-light" style="height:100vh;">

<!-- 네비게이션바 -->
<jsp:include page="../common/navBar.jsp"></jsp:include>
<div style="height: 50px"></div>

<!-- 바디 -->
<div style="width: 1200px; height: 910px; margin: auto;">
	<!-- 영화 리스트 -->
	<table Style="width:1200px; height: 840px;">
		<%int count = 0;
		for(int i=1; i<=3;i++){	%>
			<tr style>
				<%for(int j=0; j<5; j++){ 
					if(count==movieList.size()) break;
					Long contId = movieList.get(count).getContId();
					String contImg = movieList.get(count).getContImg();
					String contTitle = movieList.get(count).getContTitle();
				%>
					<td id="<%=count %>" class="inner poster" style="float: left">
						<a href="showContent?contId=<%=contId%>">
							<img class="innerImage"
								src="http://image.tmdb.org/t/p/w342<%=contImg%>"
								width="187px" height="280px">
						</a>
						<div class="title index-label">
							<%if(contTitle.length()>7){ %>
								<%=contTitle.substring(0, 5) %>...
							<%}else{ %>
								<%=contTitle %>
							<%} %>
						</div>
					</td>
				<%count++;} %>
			</tr>
		<%} %>
	</table>
	
	<!-- 페이지네이션 -->
	<div style="margin-top: 5px; width:auto;">
		<jsp:include page="allMoviePage.jsp"></jsp:include>
	</div>
</div>

<!-- 푸터 -->
<jsp:include page="../common/footer.jsp"></jsp:include>

<script src="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>