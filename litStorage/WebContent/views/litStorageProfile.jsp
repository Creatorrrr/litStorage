<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-hover table-condensed">
		<caption>메인 작가</caption>
		<colgroup>
			<col width="80" align="center">
			<col width="*">
		</colgroup>

		<tr>
			<td>아이디</td>
			<td>${litStorage.creator.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${litStorage.creator.name }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${litStorage.creator.email }</td>
		</tr>
	</table>
</body>
</html>