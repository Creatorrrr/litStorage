<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



${box1 }
	<hr>
	<a href="${ctx }/views/discussionPlaceList.jsp">토론장</a><br>
	<a href="${ctx }/litStorage/memberList.do?id=">참가 회원 목록</a><br>
	<a href="${ctx }/member/search.do?litStorageId=${litStorageId }">회원 초대</a>
	<hr>
${box2 }
	<h3>작품 등록</h3>
	<form action="${ctx }/literature/register.do" method="post" 
		name="literatureRegister" enctype="multipart/form-data" onsubmit="return checkIt()">
		<input type="hidden" name="litStorageId" value="${litStorageId }">


		<h4>장르</h4>
		<select name="selectGenre">
			<c:forEach items="${boards}" var="board">
				<option value="${board.title }">${board.title }</option>
			</c:forEach>
		</select>

		<h4>제목</h4>
		<textarea rows="1" cols="75" name="name"></textarea>

		<h4>이미지 업로드</h4>
		<input type="file" id="image" name="image">

		<h4>소개</h4>
		<textarea rows="10" cols="75" name="introduce"></textarea>
		<br>
		<button type="reset" class="btn" onclick="location.href='${ctx }/literature/list.do?id=${litStorageId }'">취소하기</button>
		<button type="submit" class="btn btn-success">작품 등록</button>

	</form>



	<script type="text/javaScript">
		function checkIt() {

			var literatureRegister = document.literatureRegister;

			if (!literatureRegister.name.value) {
				alert("제목을 입력하세요");
				return false;
			}

			if (!literatureRegister.introduce.value) {
				alert("소개를 입력하세요");
				return false;
			}
			return true;
		}

		function deleteLitFunction() {
			var deleteLit = confirm("삭제하시겠습니까?");
	
		}
	</script>

${box3 }

	
<%@ include file="footer.jspf"%>
