<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AuthorWa</title>
</head>
<body>
	<%@ include file="header.jspf"%>


	<hr>

	<!-- 메인 페이지에서 로그인시 작품 저장소 등록 버튼이 보임 -->
	<c:if test="${loginId ne null }">
		<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a>
		<br>
	</c:if>
	<!-- 메인 페이지용 작품 검색창, 검색시 이동함 -->
	<form method="get" action="${ctx }/literature/search.do">
		<label for="type">작품 검색</label>
		<select name="type" id="type">
			<option value="id">작가 아이디</option>
			<option value="name">작품 이름</option>
		</select><input type="text" name="keyword"> <input type="submit"
			name="search" id="btn" value="검색">
	</form>
	
	<!-- 장르별 추천작, 장르를 select에서 선택하면 ajax로 controller 들러서 리스트 다시 뿌려줌 -->
	<h2>장르별 추천작</h2>
	
	
	<!-- 장르별 신작, 추천작이랑 똑같이 동작함 -->
	<h2>장르별 신작</h2>

</body>
</html>