<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����</title>

  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <!-- jQuery UI -->
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
	//�޷�
	$(function() {
	    $("#datepicker").datepicker({
	      dateFormat: "yy-mm-dd"
	    });
	  });

	//���� �Ұ��� ���� ���Ѽ�
	function checkLength() {
		const maxLength = 150;
		const text = document.getElementById("myTextarea").value;
		const count = text.length;
      
		if (count > maxLength) {
			document.getElementById("myTextarea").value = text.substring(0, maxLength);
			document.getElementById("charCount").textContent = maxLength;
		} else {
		document.getElementById("charCount").textContent = count;
		}
	}
</script>
</head>
<body>

<h1>���Ӹ����</h1>
<form action="createChat" method="post">
	<b>ī�װ�</b>
		<select name="category">
			<option value="select">�����ϼ���</option>
			<option value="movie">��ȭ</option>
			<option value="book">å</option>
			<option value="ect">��Ÿ</option>
		</select>
	<br><br>
	<b>���� �̸�</b> <input type="text" placeholder="��ǰ�� / ������ / ���ӳ�¥" name="roomTitle">
	<br><br>
	<b>�ο���</b>
		<select name="amount">
			<option value="two">2</option>
			<option value="three">3</option>
			<option value="four">4</option>
			<option value="five">5</option>
		</select>
	<br><br>
	<b>���� ���</b> ���� ����<input type="text" name="loc">
	<br><br>
	<b>���� ��¥</b> <input type="text" id="datepicker" placeholder="��¥�� �����ϼ���" name="cDate">
	<br><br>
	<b>���� �Ұ���</b><br>
	<textarea id="myTextarea" rows="10" cols="30" oninput="checkLength()"></textarea>
	<span id="charCount">0</span> / 150
	<br><br>
	<input type="submit" value="�����">
	<input type="reset" value="�ʱ�ȭ">
</form>

</body>
</html>
