<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>게시글 상세 - 소설 공동작업</title>


<%@ include file="header.jspf"%>

${box1 }
	<a href="${ctx }/post/list.do?boardId=${post.board.id}" class="btn btn-primary">게시글 목록</a>

	
${box2 }
	<h3>게시글 상세</h3>
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

	<c:if test="${sessionScope.loginId ne null}">
		<c:if test="${post.writer.id eq sessionScope.loginId || sessionScope.isAdmin}">    <!--본인이 작성한 글만 수정 삭제 가능하도록   -->
			<div align="center">
				<input class="btn btn-warning" type="button" value="삭제" onclick="javascript:window.location='${ctx }/post/delete.do?postId=${post.id}'">
				<input class="btn btn-success" type="button" value="수정" onclick="javascript:window.location='${ctx}/post/modify.do?postId=${post.id }'">
			</div>
		 </c:if> 
	</c:if>
${box3 }



<%@ include file="footer.jspf"%>