<%@page import="com.moonBam.controller.board.util.ContentDataFormating"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.moonBam.dto.board.PostPageDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//데이터 뽑아 오는 곳: 기준은 아직이지만 최근 상영 중에 인기순이 가장 좋지 않을까?
	// 상위 10개 정도를 뽑아서 아래 for문 부분에 돌리기.
		List<PostPageDTO> movieList = (List<PostPageDTO>)request.getAttribute("movieList");
		List<PostPageDTO> movieMeetList = (List<PostPageDTO>)request.getAttribute("movieMeetList");
		List<PostPageDTO> movieInfoList = (List<PostPageDTO>)request.getAttribute("movieInfoList");
    	ContentDataFormating cdf = new ContentDataFormating();
%>    

<div class="  " style="" align="center">
	<div id="carouselExampleControls" class="carousel slide justify-content-center pt-5" data-bs-interval="false" style="width:1390px; height: 500px;">
		<div class="carousel-inner">
			<!-- for문 시작 -->
    		<div class="carousel-item active">
           		<div class="d-block" align="center">
 					<div class="d-flex">
						<div class="me-1 align-self-center">
							<a href="MoveToContentDetailServlet?movie=1"><img src="image/웡카.jpg" width="270px" height="382px"></a>
						</div><!-- <a>누르면 MoveToContentDetailServlet으로 이동 -->
						<div class="mx-1 align-self-center">
							<a href="ShowContentServlet"><img src="image/스즈메의문단속.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 align-self-center">
        					<a href="#"><img src="image/도그맨.jpg" width="270px" height="382px"></a>
						</div>
        				<div class="mx-1 align-self-center">
        					<a href="#"><img src="image/너의이름은.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="ms-1 align-self-center">
        					<a href="#"><img src="image/라라랜드.jpg" width="270px" height="382px"></a>
						</div>
						<!-- if문 5개 되면 새로운 carousel-item을 만들기 -->
					</div>
				</div>
			</div>
			<!-- for문 끝 -->
			
			<div class="carousel-item">
           		<div class="d-block"  align="center">
 					<div class="d-flex">
						<div class="mx-1 bg-danger align-self-center">
							<a href="MoveToContentDetailServlet?movie=1"><img src="image/라라랜드.jpg" width="270px" height="382px"></a>
						</div><!-- <a>누르면 MoveToContentDetailServlet으로 이동 -->
						<div class="mx-1 bg-light align-self-center">
							<a href="#"><img src="image/웡카.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 bg-primary align-self-center">
        					<a href="ShowContentServlet?contId=1"><img src="image/스즈메의문단속.jpg" width="270px" height="382px"></a>
						</div>
        				<div class="mx-1 bg-dark align-self-center">
        					<a href="#"><img src="image/도그맨.jpg" width="270px" height="382px"></a>
        				</div>
        				<div class="mx-1 bg-info align-self-center">
        					<a href="#"><img src="image/너의이름은.jpg" width="270px" height="382px"></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev" style="width: 100px;">
        	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
          	<span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next" style="width: 130px">
          	<span class="carousel-control-next-icon" aria-hidden="false"></span>
         	<span class="visually-hidden">Next</span>
        </button>
	</div>
	
	

	<div class="d-flex justify-content-center px-5" style="height: 300px">
		<div class="mx-1">
			<table border="1" style="width:450px">
				<tr><th style="height:30px"><a href="/acorn/board/movieInfo">정보게시판</a></th></tr>
		        <% 
		            for(PostPageDTO post : movieInfoList) {
		            	String displayDate = cdf.minuteHourDay(post);
		        %>
		                <tr>
		                    <td style="height:54px">
		                    <a href="/acorn/board/content?postId=<%= post.getPostId() %>&bn=<%= post.getPostBoard()%>">
		                        <%= post.getPostTitle() %></a> [<%= post.getCommentCount() %>]<br>
		                        
		                     <%= displayDate %> |<%= post.getViewNum() %> | <%= post.getLikeNum() %>
		                    
		                    </td>
		                </tr>
		        <% 
		            }
		        %>
			</table>
		</div>
		<div class="mx-1">
		    <table border="1" style="width:450px">
		        <tr><th style="height:30px"><a href="/acorn/board/movie">자유게시판</a></th></tr>
		        <% 
		            for(PostPageDTO post : movieList) {
		            	String displayDate = cdf.minuteHourDay(post);
		        %>
		                <tr>
		                    <td style="height:54px">
		                    <a href="/acorn/board/content?postId=<%= post.getPostId() %>&bn=<%= post.getPostBoard()%>">
		                        <%= post.getPostTitle() %></a> [<%= post.getCommentCount() %>]<br>
		                        
		                     <%= displayDate %> |<%= post.getViewNum() %> | <%= post.getLikeNum() %>
		                    
		                    </td>
		                </tr>
		        <% 
		            }
		        %>
		    </table>
		</div>

		<div class="mx-1">
			<table border="1" style="width:450px">
				<tr><th style="height:30px"><a href="/acorn/board/movieMeet">모임게시판</a></th></tr>
		        <% 
		            for(PostPageDTO post : movieMeetList) {
		            	String displayDate = cdf.minuteHourDay(post);
		        %>
		                <tr>
		                    <td style="height:54px">
		                    <a href="/acorn/board/content?postId=<%= post.getPostId() %>&bn=<%= post.getPostBoard()%>">
		                        <%= post.getPostTitle() %></a> [<%= post.getCommentCount() %>]<br>
		                        
		                     <%= displayDate %> |<%= post.getViewNum() %> | <%= post.getLikeNum() %>
		                    
		                    </td>
		                </tr>
		        <% 
		            }
		        %>
			</table>
		</div>
	</div>
	
</div>