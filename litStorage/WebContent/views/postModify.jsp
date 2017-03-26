
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<title>게시글 수정</title>
</head>
<body>
	<a href="${ctx }/post/list.do?boardId=${post.board.id}">게시글 목록</a>
	<div class="col-xs-12 col-md-8">
		<h3>게시글 수정</h3>
		<div class="text-right"></div>
		<div class="panel panel-default">
			<div class="row">
				<form action="${ctx }/post/modify.do" method="post">
					<input type="hidden" name="postId" value="${post.id }">
					<div class="col-md-3">
						<br>
						<div>
							<h4>제목</h4>
							<textarea rows="1" cols="75" name="title">${post.title}</textarea>
						</div>
						<div>
							<h4>내용</h4>
							<textarea rows="10" cols="75" name="content">${post.content}</textarea>
						</div>
						<div>
							<h4>해시태그</h4>
							<textarea rows="1" cols="75" name="hashtag">${post.hashTag}</textarea>
						</div>
						<button type="reset">취소</button>
						<button type="submit" class="btn">완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


