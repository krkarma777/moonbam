<%@page import="org.apache.ibatis.reflection.SystemMetaObject" %>
<%@page import="com.moonBam.dto.CommentDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@page import="com.moonBam.dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">


window.onload = init;


    function init() {

    	
        function clock() { //작성시간을 실시간으로 보여주기 위한 함수
            var time = new Date();

            var year = time.getYear();
            var month = time.getMonth();
            var date = time.getDate();
            var day = time.getDay();
            var hours = time.getHours();
            var minutes = time.getMinutes();
            var seconds = time.getSeconds();

            $("#comdate").text((year - 100) + "년 " + (month + 1) + "월 " + date + "일 " + (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds));


        }

        clock();
        setInterval(clock, 1000); // 1초마다 실행


    }//init


    $(document).ready(function () {

        document.get
        // 페이지 로딩 시 댓글 목록을 불러옴
        CommentSelectAllServlet();

        $("#sendButton").click(function () {
            $.ajax({

                type: "post",
                url: "/acorn/Acorn/CommetInsert",
                data: {
                    postId: $("#postidComment").val(),
                    //userId: '<sec:authentication property="name"/>',
                    comDate: $("#comdate").text(),
                    comText: $("#comtext").val(),
                    //nickname: '<sec:authentication property="name"/>',
                    aboveComId: $("#abovecomidComment").val()
                },
                success: function (data, status, xhr) {
					
                    CommentSelectAllServlet();

                },
                error: function (xhr, status, error) {

                    $("#CommetList").text(error);
                    $("#CommetList").text(status);

                }

            })//ajax

        });//sendButton end


    });//doc end 1


    $(document).on("click", ".deleteCommentBtn", function () {

        var comId = $(this).attr("id");
        var aboveComId = $(this).attr("data-xxx");

        var replyJson2 = ReplyCommentSearch(comId);
        var length2 = replyJson2.length;

        if (length2 != 0) {//자식댓글이 있다는 뜻  update 치면 됨

            //내 comId랑 동일한 avobeComId를 가지고 있는 레코드가 있으면?????? ----> "삭제된 댓글입니다." 로 UPDATE

            $.ajax({

                type: "post",
                url: "/acorn/Acorn/CommentUpdate",
                data: {
                    //댓글 수정(삭제)를 위해서 서버로 comid를 넘겨주고 있음
                    comId: $(this).attr("id"),
                    How: "0"
                    //아래 수정버튼 진행 시 똑같이 CommentUpdate로 넘어가기 때문에 구분짓기 위해서 HOW라는 KEY=VALUE를 이용함

                },
                dataType: "text",
                success: function (data, status, xhr) {
                    console.log(data);

                    if (data == "댓글이 삭제되었습니다.") {
                        alert(data);
                        CommentSelectAllServlet();

                    } else {
                        alert("댓글을 삭제할 수 없습니다.");
                    }

                },
                error: function (xhr, status, error) {
                    console.log("에러코드 add" + status);
                }

            })//update ajax


        } else {

            //내 comId랑 동일한 avobeComId를 가지고 있는 레코드가 없으면????? -----> 그냥 DELETE

            $.ajax({

                type: "post",
                url: "/acorn/Acorn/CommetDelete",
                data: {
                    //댓글 삭제를 위해서 서버로 comid를 넘겨주고 있음
                    comId: comId
                },
                dataType: "text",
                success: function (data, status, xhr) {
                    console.log(data);

                    if (data == "댓글이 삭제되었습니다.") {
                        alert(data);
                        CommentSelectAllServlet();

                    } else {
                        alert("댓글을 삭제할 수 없습니다.");
                    }

                },
                error: function (xhr, status, error) {
                    console.log("에러코드 add" + status);
                }

            })//delete ajax

        }//else end


    });//deleteCommentBtn doc end


    $(document).on("click", ".updateCommentBtn", function () {
        //수정 버튼 눌러서 내용 수정할 수 있게 화면이 바뀌는 기능

        var comId = $(this).attr("data-xxx");
        //console.log("updateCommentBtn 이벤트의",comId);

        $("#comText" + comId).removeAttr("style");
        $("#comText" + comId).removeAttr("readonly");
        //input태그 그냥 text처럼 보이게 하기 위해 줬던 속성 수거하는 중 (수정하려는 val값 수정할 수 있게)

        $("#update" + comId).attr("style", "display: none");
        //수정버튼이 눌리면 수정버튼은 잠깐 안 보이게 하기 위함 (레이아웃에서까지 없애야해서 display 속성을 사용)

        $("#span" + comId).html("<button class='btn btn-primary btn-sm btn-spacing updateCommentBtn2' data-xxx='" + comId + "'>확인</button>");
        //실제로 update기능이 실행될 [확인] 버튼을 span태그 안에 넣어주는중

    })//updateCommentBtn doc end


    $(document).on("click", ".updateCommentBtn2", function () {
        //찐으로 update가 진행될 수 있는 [확인]이 실행되는 기능

        var comId = $(this).attr("data-xxx");

        $.ajax({

            type: "post",
            url: "/acorn/Acorn/CommentUpdate",
            data: {
                //댓글 수정(삭제)를 위해서 서버로 comid를 넘겨주고 있음
                comId: $(this).attr("data-xxx"),
                comText: $("#comText" + comId).val(),
                How: "1"
                //위에 삭제버튼 진행 시 똑같이 CommentUpdateServlet로 넘어가기 때문에 구분짓기 위해서 HOW라는 KEY=VALUE를 이용함
                //0이면 delete(update) 이고 1이면 찐update임
            },
            dataType: "text",
            success: function (data, status, xhr) {
                //console.log(data);

                if (data == "댓글이 수정되었습니다.") {
                    alert(data);
                    $("#span" + comId).html(""); //[확인]버튼 다시 없애버리는중
                    $("#update" + comId).removeAttr("style"); //[수정]버튼 숨겨놨던 속성 지워버리기
                    CommentSelectAllServlet();

                } else {
                    alert("댓글을 수정할 수 없습니다.");
                }

            },
            error: function (xhr, status, error) {
                console.log("에러코드 add" + status);
            }

        })//AJAX END

    })//updateCommentBtn2 END


    function CommentSelectAllServlet() {
        //console.log("CommentSelectAllServlet");

        var postId = <%= request.getParameter("postId")%>
            console.log("아아아", postId);


        $.ajax({

            type: "post",
            url: "/acorn/Acorn/selectAllByPostId",
            data: {
                postId: postId // postid 값을 요청에 포함시킴
            },
            dataType: "json",
            success: function (data, status, xhr) {
                var mesg = "";
                var length = data.length;
                var comId = 0;
                // 댓글이 없는 경우
                if (length == 0) {
                    mesg = "<div class='text-center'>댓글이 없습니다.</div>";
                } else {
                    mesg = "<ul class='comment-list'>"; // 댓글 리스트 시작

                    for (var i = 0; i < length; i++) { //부모댓글 for문 (바깥for문)

                        ///부모댓글 목록 뿌리기 용///
                        var comId = data[i].comId;
                        var userId = data[i].userId;
                        var comDate = data[i].comDate;
                        var comText = data[i].comText;
                        var nickname = data[i].nickname;
                        var aboveComId = data[i].aboveComId; //부모댓글번호

                        console.log("commentseletall에서   ", comId, " ", userId, " ", comDate, " ", nickname, " ", aboveComId);
                        ///////////

                        ///답글 작성용 변수///
                        var nickname2 ='<sec:authentication property="name"/>';
                        var userId2 = '<sec:authentication property="name"/>';
                        ///답글 작성용 변수///

                        if (aboveComId == null) { //aboveComId == 부모댓글ID 이게 NULL이면 부모 댓글이라는 뜻 (자식 댓글이 아님)

                            // 각 댓글 항목 (부모)//
                            mesg += "<li class='comment-item'>";
                            mesg += "<div class='comment-meta'>";
                            mesg += "<input type='hidden' id='commIdAfterSelAll" + comId + "' value='" + comId + "'>"
                            mesg += "<input type='hidden' id='aboveComIdAfterSelAll" + comId + "' value='" + aboveComId + "'>"
                            mesg += "<strong>" + nickname + "</strong> | <span>" + comDate + "</span>";
                            mesg += "</div>";
                            mesg += "<input type='text' class='comment-content' style='border:none;outline:none' id='comText" + comId + "' readonly value='" + comText + "'>";

                            if (comText != "삭제된 댓글입니다.") { //삭제된 댓글이라 뜨는 댓글엔 답글 못 달게 했음
                                mesg += "<br><a href='#'  onclick='replyComment(" + comId + ");return false;'>답글</a>";
                            }//if(comText!="삭제된 댓글입니다.") end
                            
                            
                          	//지금 로그인하고 있는 사용자 id에 @ . 이 html 인코딩 처리되어 유니코드로 나와서
                        	//이 부분을 디코드 해서 저장시킨 [현재 사용자의 id]임
                        	var enCodeUserId = '<sec:authentication property="name"/>'
                        	var deCodeUserId = enCodeUserId.replace("&#64;","@").replace("&#46;",".")

                            if (userId == deCodeUserId) {

                                if (comText != "삭제된 댓글입니다.") {
                                    mesg += "<div class='comment-actions'>";
                                    mesg += "<button id='" + comId + "' class='btn btn-danger btn-sm btn-spacing deleteCommentBtn' data-xxx='" + aboveComId + "'>삭제</button>";
                                    mesg += "<button id='update" + comId + "' class='btn btn-info btn-sm btn-spacing updateCommentBtn' data-xxx='" + comId + "'>수정</button>";
                                    mesg += "<span id='span" + comId + "'></span>"; //수정버튼 눌렀을 때 이 자리에 [확인] 버튼이 오게 될 것임
                                    mesg += "</div>";

                                }//안쪽if end
                            }//바깥if end

                            //답글 적는 란 start *평소엔 숨겨져있다가 [답글]버튼 누르면 활성화 되고, [답글 작성] 버튼 누르면 다시 사라짐
                            mesg += "<div style='display:none' id='replyCommentDIV" + comId + "'>";
                            mesg += "&nbsp;&nbsp;&nbsp;<input type='hidden' id='replyCommentId" + comId + "' name='replyuserId' value=" + userId2 + ">"
                           // mesg += "&nbsp;&nbsp;&nbsp;<strong id='strong" + comId + "'>" + nickname2 + "</strong>";
                            mesg += "<br>"
                            mesg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='replyCommentComtext" + comId + "' name='replyCommentComtext'></textarea>"; //답글 다는 창
                            mesg += "&nbsp;&nbsp;&nbsp;<input type='button' id='replyButton' value='등록' onclick='replyCommentInsert(" + comId + ")' class='btn btn-secondary btn-sm btn-spacing' style='margin-top: 10px;'>"
                            mesg += "</div>";
                            //////////////


                            ////부모 댓글 목록 뿌리기 END/////

                        }//if(aboveComId==null) end //부모댓글 종료

                        mesg += "</li>";


                        ////자식 댓글 뿌리기 시작////
                        var replyJson = ReplyCommentSearch(comId); //자식댓글 있는지 확인

                        if (replyJson == null) { //자식댓글이 없다면!!

                            console.log("답글 없어.");

                        } else {

                            var length2 = replyJson.length;

                            for (var j = 0; j < length2; j++) {
                                ///자식 댓글 목록 뿌리기 용///

                                comId = replyJson[j].comId;
                                userId = replyJson[j].userId;
                                comDate = replyJson[j].comDate;
                                comText = replyJson[j].comText;
                                nickname = replyJson[j].nickname;
                                //console.log("닉네임",nickname)
                                aboveComId = replyJson[j].aboveComId; //부모댓글번호

                                mesg += "<li class='comment-item'>";
                                mesg += "<div class='comment-meta'>";
                                mesg += "&nbsp;&nbsp;&nbsp;<input type='hidden' id='commIdAfterSelAll" + comId + "' value='" + comId + "'>"
                                mesg += "&nbsp;&nbsp;&nbsp;<input type='hidden' id='aboveComIdAfterSelAll" + comId + "' value='" + aboveComId + "'>"
                                mesg += "&nbsp;&nbsp;&nbsp;<strong>" + nickname + "</strong> | <span>" + comDate + "</span>";
                                mesg += "</div>";
                                mesg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' class='comment-content' style='border:none;outline:none' id='comText" + comId + "' readonly value='" + comText + "'>";

                                if (userId == "<sec:authentication property="name"/>") {

                                    if (comText != "삭제된 댓글입니다.") {
                                        mesg += "<div class='comment-actions'>";
                                        mesg += "<button id='" + comId + "' class='btn btn-danger btn-sm btn-spacing deleteCommentBtn' data-xxx='" + aboveComId + "'>삭제</button>";
                                        mesg += "<button id='update" + comId + "' class='btn btn-info btn-sm btn-spacing updateCommentBtn' data-xxx='" + comId + "'>수정</button>";
                                        mesg += "<span id='span" + comId + "'></span>"; //수정버튼 눌렀을 때 이 자리에 [확인] 버튼이 오게 될 것임
                                        mesg += "</div>";

                                    }//안쪽if end
                                }//바깥if end

                                mesg += "</li>";

                            }//답글 for end

                        }// if(replyJson.replyCommentDBList.length == 0) 자식댓글이 있으면!! 의 else end


                    }//부모 댓글 for end

                    mesg += "</ul>"; // 부모 댓글 리스트 종료

                }//else end

                $("#CommetList").html(mesg); //아래 출력하기

            },//success end

            error: function (xhr, status, error) {
                console.log("에러코드 selectAll", xhr);
            }

        });//selectAll ajax

        //댓글 입력 후 텍스트 칸 비워주는 코드
        $("#comtext").val("");

    }//CommentSelectAllServlet end

    function replyComment(comId) { //답글 버튼 눌렀을 때 실행되는 onclick ReplyComment 함수
        //console.log("ReplyComment 확인용 ",comId);

        $("#replyCommentDIV" + comId).attr("style", "display:hidden");
        //답글 입력하는 창(코드)이 다시 보이게 display속성 바꿔줬음.

    }//replyComment end

    function replyCommentInsert(comId) {

        var aboveComId = comId;
        var nickname2 = $("#strong" + comId).text();
        var userId2 = $("#replyCommentId" + comId).val();
        var postId = <%= request.getParameter("postId")%>
            console.log("replyCommentInsert에서" + nickname2, userId2, postId);

        $.ajax({

            type: "post",
            url: "/acorn/Acorn/ReplyCommentInsert",
            data: {
                aboveComId: aboveComId, //부모댓글id
                postId: postId, //게시글
                //userId: userId2, //내 아이디, 부모댓글의 id가 아님
                comText: $("#replyCommentComtext" + comId).val(),
                //nickname: nickname2
                //comdate는 어차피 날짜스탬프로 sql날릴 거라 안 넘어감
            },
            success: function (data, status, xhr) {

                //console.log("성공");
                CommentSelectAllServlet();
            },
            error: function (xhr, status, error) {

                $("#CommetList").text(error);
                $("#CommetList").text(status);

            }//error end

        })//ajax


    }//replyCommentSend end


    function ReplyCommentSearch(comId) {
        var replyJson = {};

        $.ajax({

            type: "post",
            async: false,
            url: "/acorn/Acorn/ReplyCommentSelectList",
            data: {
                comId: comId
            },
            dataType: "json",
            success: function (data, status, xhr) {

                replyJson = data;

            },//success end,
            error: function (xhr, status, error) {

                $("#CommetList").text(error);
                $("#CommetList").text(status);

            }//error end

        })//ajax

        return replyJson; //select결과 return

    }//ReplyCommentSearch end


</script>

<input type="hidden" id="postidComment" name="postid"
       value=<%=request.getParameter("postId")%>> <!-- 굳이 고객한테 보일 필요가 없으니 hidden 태그 -->
<input type="hidden" id="useridComment" name="userid" value="${sessionScope.loginUser.userId}"> <!--DB저장용 userid -->
<input type="hidden" id="abovecomidComment" name="abovecomid" value="">

<div id="CommetList" style="margin-top: 20px;">

    <!--작성된 댓글 목록 보기-->

</div>
<div class="card mt-4">
    <div class="card-header">댓글 작성</div>
    <div class="card-body">
        <span id="nickname" name="nickname">
            <sec:authorize access="isAnonymous()">
                <!-- 비로그인 상태의 메시지 -->
                로그인을 해주세요.
            </sec:authorize>
        </span>
        <br>
        <textarea id="comtext" class="form-control" name="comtext" style="height: 100px;"
                  placeholder="댓글을 입력하세요. 지나친 욕설/비방 작성 시 사이트 이용에 제재를 받을 수 있습니다."
                  <sec:authorize access="isAnonymous()">disabled</sec:authorize>>
        </textarea>
        <div style="text-align: right">
            <sec:authorize access="isAuthenticated()">
                <input type="button" id="sendButton" value="등록" class="btn btn-action btn-spacing"
                       style="margin-top: 10px;">
            </sec:authorize>
        </div>
    </div>
</div>