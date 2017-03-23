<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="post" value="${post}" /> 
<title>게시글 상세</title>
<%
		String postId =  request.getParameter("id");
%>
</head>
<body>

	<a href="${ctx }/views/postList.jsp">게시글 목록</a>
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
		 <div align="center">
		<input class="btn"  value="삭제" type="button" onclick="javascript:window.location='${ctx }/postDelete.do?id=${post.id}'"> 
		<input class="btn" type="submit" value="수정">
		<!-- <input class="btn btn-success" type="submit" value="등록하기"></div> --> 
		</div> 
	<br>
</body>
</html>