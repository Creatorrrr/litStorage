<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>게시글 수정 - 소설 공동작업</title>


<%@ include file="header.jspf"%>


${box1 }
	<a href="${ctx }/post/list.do?boardId=${post.board.id}" class="btn btn-primary">게시글 목록</a>
	
${box2 }


		<h3>게시글 수정</h3>
		<form action="${ctx }/post/modify.do" method="post">
			<input type="hidden" name="postId" value="${post.id }">

			<h4>제목</h4>
			<textarea rows="1" cols="75" name="title">${post.title}</textarea>

			<h4>내용</h4>
			<textarea rows="10" cols="75" name="content">${post.content}</textarea>

			<h4>해시태그</h4>
			<textarea rows="1" cols="75" name="hashtag">${post.hashTag}</textarea>
			<br><br>

			<button type="reset" class="btn">취소</button>
			<button type="submit" class="btn btn-success">완료</button>

		</form>

${box3 }

<%@ include file="footer.jspf"%>