<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html lang="ko">
	<head>
	<title>작품 저장소 등록</title>
	<meta charset="utf-8">
	<link href="resources/css/bootstrap_modify.css" rel="stylesheet">
	<link href="resources/css/layout.css" rel="stylesheet">
	<script src="resources/js/jquery-2.1.3.js"></script>
	</head>
	<body>
		<form action="${ctx }/litStorage/register.do" method="post">
			<table>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" maxlength="30"></td>
				</tr>
				<tr>
					<td>소개</td>
					<td><input type="text" name="introduce" maxlength="300"></td>
				</tr>
			</table>
			<button type="submit">등록</button>
		</form>
	</body>
</html>