<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jspf"%>
<h1>이 밑에서 버튼 만들어서 기능구현 테스트 해주세요~</h1>
<a href="${ctx }/litStorage/allList.do">작품 저장소 목록</a>
</body>
</html>