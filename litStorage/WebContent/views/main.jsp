<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>

</head>
<body>
<c:choose>
	<c:when test = "${loginedUser eq null }">
		<a href="login.jsp">로그인</a> | <a href="join.do">회원가입</a>
	</c:when>
	<c:otherwise>
		<b>${loginedUser }</b>님 환영합니다. [<a href="logout.do">로그아웃</a>]
	</c:otherwise>
</c:choose>
	
	
</body>
</html>