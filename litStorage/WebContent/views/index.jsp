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
<a href="${ctx }/litStorage/allList.do">작품 저장소 목록</a><br>
<a href="${ctx }/episode/list.do">연재글 목록</a><br>
<a href="${ctx }/views/episodeRegister.jsp">연재글 등록</a><br>
<a href="${ctx }/episode/detail.do">연재글 상세</a><br>
<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a><br>
<a href="${ctx }/litStorage/myList.do">내 작품 저장소!</a><br>
<a href="${ctx }/litStorage/memberList.do?id=4">작품 저장소 참가 회원</a><br>
<a href="${ctx }/board/freeBoard.do">자유 게시판</a><br>
<a href="${ctx }/litStorage/allList.do">작품 저장소 목록</a><br>
<a href="${ctx }/discussionPlace/list.do">토론장 목록</a><br>

</body>
</html>