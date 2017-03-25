
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<c:set var="post" value="${post}" />
<title>게시글 수정</title>
<%
	String postId = request.getParameter("id");
%>
</head>
<body>

	<a href="${ctx }/post/postList.do">게시글 목록</a>
	<div class="col-xs-12 col-md-8">
		<h3>게시글 수정</h3>
		<div class="text-right"></div>
		<div class="panel panel-default">
			<div class="row">
				<form action="${ctx }/postModify.do" method="post">
					<input type="hidden" name="modifyPostId" value="${post.id }">

					<div class="col-md-3">
						<br>
						<!-- 	<table class="table"> -->
						<colgroup>
							<col width="150">
							<col width="*">
						</colgroup>
						<div>
							<h4>제목</h4>
							<textarea rows="1" cols="75" name="modifyPostTitle">${post.title}</textarea>
						</div>
						<div>
							<h4>내용</h4>
							<textarea rows="10" cols="75" name="modifyPostContent">${post.content}</textarea>
						</div>
						<div>
							<h4>해시태그</h4>
							<textarea rows="1" cols="75" name="modifyPostHashtag">${post.hashTag}</textarea>
						</div>
						<!-- </table> -->
						<button type="reset">취소</button>
						<button type="submit" class="btn">완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<div align="center">
		<%-- <button type="reset">취소</button>
원래 이거 <input class="btn" type="button" value="수정"
			onclick="javascript:window.location='${ctx}/postModify.do?id=${post.id }'"> --%>



		<!-- <input class="btn btn-success" type="submit" value="등록하기"></div> -->
	</div>
	<br>
</body>
</html>


