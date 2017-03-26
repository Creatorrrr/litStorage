<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>게시글 등록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


	<a href="${ctx }/post/list.do">게시글 목록</a>
	<h3>게시글 등록</h3>

	<br>
	<form action="${ctx }/post/register.do" method="post" name="pregister"
		onsubmit="return checkIt()">
		<input name="boardId" type="hidden" value="${boardId }">
		<table class="table">
			<colgroup>
				<col width="150">
				<col width="*">
			</colgroup>
			<tr>
				<th>제목</th>
				<td>
					<input id="postTitle" name="postTitle" class="form-control"
					type="text" value="">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="postContent" name="postContent"
						class="form-control" rows="7"></textarea>
			</tr>
			<tr>
				<th>해시태그</th>
				<td><input id="hashtag" name="hashtag" class="form-control"
					type="text" value=""></td>
			</tr>

		</table>
		<br>
		<div align="center">
			<input class="btn" type="reset" value="취소하기"
				onclick="javascript:window.location='${ctx }/post/list.do?boardId=${boardId }'">
			<input class="btn" type="submit" value="등록하기"">
		</div>
	</form>
	<br>
	<script type="text/javaScript">
		function checkIt() {

			var pregister = document.pregister;

			if (!pregister.postTitle.value) {
				alert("제목을 입력하세요");
				return false;
			}

			if (!pregister.postContent.value) {
				alert("내용을 입력하세요");
				return false;
			}
			return true;
		}
	</script>

	
<%@ include file="footer.jspf"%>