<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #reportForm {
            display: none;
            position: fixed;
            width: 300px;
            height: 200px;
            padding: 10px;
            border: 1px solid black;
            background-color: white;
        }
    </style>
</head>
<body>
	<body>
    <form action="#" method="post">
        <label><input type="checkbox" name="reason" value="reason1"> Reason 1</label><br>
        <label><input type="checkbox" name="reason" value="reason2"> Reason 2</label><br>
        <label><input type="checkbox" name="reason" value="reason3"> Reason 3</label><br>
        <label><input type="checkbox" name="reason" value="reason4"> Reason 4</label><br>
        <label><input type="checkbox" name="reason" value="reason5"> Reason 5</label><br>
        <textarea name="reportText" maxlength="200"></textarea><br>
        <input type="submit" value="Submit">
    </form>
</body>
</body>
</html>