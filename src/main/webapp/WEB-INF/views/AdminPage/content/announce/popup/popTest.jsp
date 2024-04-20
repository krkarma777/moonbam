<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문화인들의 밤</title>
<script type="text/javascript">
// 사용자 모니터 화면 크기 
let count = 0;
let screenWidth;
let screenHeight;

let sLeft=0;
let sTop=0;
let width;
let height;
    
function getScreenSize() {
    screenWidth = screen.width;
    screenHeight = screen.height;
    document.getElementById("screenSize1").innerHTML = "스크린 너비: " + screenWidth + ", 스크린 높이: " + screenHeight;
}
window.onload = getScreenSize;

$(function() {
    $("#but").on("click", butFunc);
    
    function butFunc() {
        // 창의 크기
        width = (screenWidth / 4);
        height = (screenHeight / 3);
        
        // 창의 시작점
        sLeft = width * (count % 4);    
        sTop = height * Math.floor(count / 4);
        
        console.log("sLeft: " + sLeft + ", sTop: " + sTop);
        window.open("ViewPopupController?num=" + count, "popup" + count, "left=" + sLeft + ",top=" + sTop + ",width=" + width + ",height=" + height);
        count++;
    }
});
</script>
</head>
<body>
<h1>스크린 사이즈 출력</h1>
<p id="screenSize1"></p>
<button id="but">but</button>
</body>
</html>