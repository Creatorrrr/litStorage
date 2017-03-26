<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<title>게시글 상세</title>
</head>
<body>
<%@ include file="header.jspf"%>
	<a href="${ctx }/post/list.do?boardId=${post.board.id}">게시글 목록</a>
	<h3>게시글 상세</h3>
	<br>
	<table class="table">
		<colgroup>
			<col width="150">
			<col width="*">
		</colgroup>
		<tr>
			<th>제목</th>
			<td>${post.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${post.content}</td>
		</tr>
		<tr>
			<th>해시태그</th>
			<td>${post.hashTag}</td>
		</tr>
	</table>
	<br>
	<c:if test="${sessionScope.loginId ne null}">
		<c:if test="${post.writer.id eq sessionScope.loginId || sessionScope.isAdmin}">    <!--본인이 작성한 글만 수정 삭제 가능하도록   -->
			<div align="center">
				<input class="btn" type="button" value="삭제"
					onclick="javascript:window.location='${ctx }/post/delete.do?postId=${post.id}'">
				<input class="btn" type="button" value="수정"
					onclick="javascript:window.location='${ctx}/post/modify.do?postId=${post.id }'">
			</div>
		 </c:if> 
	</c:if>
</body>
</html>