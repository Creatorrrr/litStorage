<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<title>Insert title here</title>
</head>
<body>
	<!--  Main member table start-->
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
	<!-- Main member table over -->
	<!-- Sub member table start-->
	<table class="table table-hover table-condensed">
		<caption>참여 작가</caption>
		<colgroup>
			<col width="80" align="center">
			<col width="*">
		</colgroup>
		<c:forEach items="${ litStorage.participants}" var="member">
			<tr>
				<td>아이디</td>
				<td>${member.id }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.email }</td>
			</tr>
		</c:forEach>
	</table>
	<!-- sub member table over -->
</body>
</html>