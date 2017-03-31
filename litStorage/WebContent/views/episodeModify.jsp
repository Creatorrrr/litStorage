<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 수정 - 소설 공동작업</title>

<%@ include file="header.jspf"%>

<c:choose>
	<c:when test="${literature.creator.id eq loginId}">
		<c:set var="isMaster" value="true" />
	</c:when>
	<c:otherwise>
		<c:set var="isMaster" value="false" />
	</c:otherwise>
</c:choose>
 


${box1 }
	<jsp:include page="litStorageSideNav.jsp">
		 <jsp:param name="litStorage" value="${episode.literature.litStorage.id }"/>
		 <jsp:param name="isMaster" value="${isMaster }"/>
		 <jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>

${box2 }
	<h3>[${literature.litStorage.name}] ${literature.name }의 연재글 수정</h3>
	<form action="${ctx }/episode/modify.do" method="post" name="eModify" onsubmit="return checkIt()">
		<input type="hidden" name="episodeId" value="${episode.id }">

		<h4>제목</h4>
		<textarea  class="form-control" name="episodeTitle">${episode.title }</textarea>

		<h4>내용</h4>
		<textarea  class="form-control" style="min-height:300px;" name="episodeContent">${episode.contentFromGit }</textarea>
		<br>
		<div class="text-center">
			<button type="reset" onclick="location.href='${ctx }/episode/detail.do?episodeId=${episode.id}'" class="btn btn-sm">취소하기</button>
			<button type="submit" class="btn btn-sm btn-success">연재글 수정</button>
		</div>
	</form>


	<script type="text/javaScript">
		function checkIt() {

			var eModify = document.eModify;

			if (!eModify.episodeTitle.value) {
				alert("제목을 입력하세요");
				return false;
			}

			if (!eModify.episodeContent.value) {
				alert("내용을 입력하세요");
				return false;
			}
			return true;
		}
	</script>

${box3 }


<%@ include file="footer.jspf"%>