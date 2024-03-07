<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid" align="center">
        <div class="d-flex justify-content-center" style="height: ;">
            <div class=" bg-danger text-bg-danger align-self-center" style="">
                <div class="bg-black h-50"><img src="image/웡카.jpg" width="230px" height="330px"></div>
                <div class="bg-dark h-50">영화 정보</div>
            </div>
            <div class=" bg-light align-self-center">
                영화 정보
            </div>
        </div>
        
        <table align="center" border="1" class="mt-1">
            		<%for(int j=1; j<=2; j++){ %>
					<tr>
						<%for(int k=1; k<=4; k++){%>
						<td>
							<div class="">
								<div class="border-bottom" style="height: 25px; width: 250px;">글쓴이 정보</div>
								<div style="height: 180px; width: 250px;">내용</div>
								<div class="border-top " style="height: 30px; width: 250px;">좋아요</div>
							</div>
						</td>
						<%} %>
					</tr> 
					<%} %>
		</table>
    </div>