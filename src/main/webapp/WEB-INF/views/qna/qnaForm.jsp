<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문화인들의 밤</title>
    <style>
        .ck-editor__editable { height: 400px; }
    </style>
</head>
<body>
    <form action="submit_qna" method="POST">
        제목:<input type="text" name = "qna_title" style="width: 500px;">
        <hr>
        <textarea name="qna_text" id="editor"></textarea>
        <p><input type="submit" value="작성"><button>취소</button></p>
    </form>

    <script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
    <script>
        ClassicEditor.create( document.querySelector( '#editor' ), {language:"ko"} );
    </script>


</body>
</html>