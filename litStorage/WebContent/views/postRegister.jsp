<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록</title>
</head>
<body>

	<input id="postId" name="postId" type="hidden" value="">
	<a href="list.do">게시글 목록</a>
	<h3>게시글 등록</h3>
	
	<br>
	<form action="postRegister.do" method="post" >
		<table class="table">
            <colgroup>
                <col width="150">
                <col width="*">
            </colgroup>
			<tr>
				<th>제목</th>
				<td><input id="lectureName"  name="lectureName" class="form-control" type="text" value=""></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="lectureIntroduce" name="lectureIntroduce" class="form-control" rows="7"></textarea>
			</tr>
			<br>
			<br>
			<br>
			<tr>
				<th>해시태그</th>
				<td><input id="instructorName" name="instructorName" class="form-control" type="text" value=""></td>
			</tr>
			
		</table>
        <br>
		<div align="center">
		<input class="btn" type="reset" value="취소하기"> 
		<input class="btn btn-success" type="submit" value="등록하기"></div>
	</form>
	<br>


</body>
</html>