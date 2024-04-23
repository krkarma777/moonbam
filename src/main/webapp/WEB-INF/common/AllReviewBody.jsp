<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="fullpage/jquery.fullPage.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="fullpage/jquery.fullPage.js"></script>
<script>
$(document).ready(function() {
	$('#fullpage').fullpage({
		//options here
		autoScrolling:true,
		scrollHorizontally: true,
        navigation:true,
	});
});
</script>
<%
	//데이터 받아오는 곳
	//글쓴이 정보, 내용 등등
%>
<main id="fullpage" class="">
	<!-- <table>
		<tr> -->
			<!-- for문 시작 -->
			<% int count = 0;
			for(int i=1; i<=3; i++){ %><!-- 3은 총 리뷰갯수 가져와서 %12로 남으면 +1 -->
        	<section class="section">
            	<table align="center" border="1">
            		<%for(int j=1; j<=3; j++){ %>
					<tr>
						<%for(int k=1; k<=4; k++){
							if(27==count)break;
							count++;%>
						<td>
							<div class="border rounded-2">
								<div class="border-bottom" style="height: 25px; width: 250px;">글쓴이 정보</div>
								<div style="height: 180px; width: 250px;">내용</div>
								<div class="border-top " style="height: 30px; width: 250px;">좋아요</div>
							</div>
						</td>
						<%} %>
					</tr> 
					<%} %>
				</table>
        	</section>
        	<%} %>
        <!-- </tr>
	</table> -->
</main>


<%-- <div class="container-fluid pt-3" align="center">
        <table>
            <tr>
                <!-- start for -->
                <%for(int i=1; i<=25; i++){ %>
                <td>
                    <table style="width: 320px; height: 240px;">
                        <tr>
                            <td>
                            	<div class="border rounded-2" style="color:white;">
                            		<div class="border-bottom" style="height: 25px; ">글쓴이 정보</div>
                            		<div style="height: 200px;">내용</div>
                            		<div class="border-top " style="height: 25px;">좋아요</div>
                            	</div>
                            </td>
                        </tr> 
                    </table>
                </td>
                <!-- if(i%4==0){ -->
                <%if(i%4==0){ %>
                <tr>
                    <td style="height: 10px;"></td>
                </tr>
                <!-- }end if -->
                <!-- end for -->
                <%}} %>
            </tr>
        </table>
</div> --%>