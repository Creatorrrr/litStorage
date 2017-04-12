<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 목록 - 소설 공동작업</title>

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
		 <jsp:param name="litStorage" value="${literature.litStorage.id }"/>
		 <jsp:param name="isMaster" value="${isMaster }"/>
		 <jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>


${box2 }
	<h3>[${literature.litStorage.name}] ${literature.name }의 연재 글 등록</h3>

	<form action="${ctx }/episode/register.do" method="post" name="eRegister" onsubmit="return checkIt()">
		<input type="hidden" name="literatureId" value="${literatureId }">

			<h4>제목</h4>
			<textarea rows="1" cols="75" name="episodeName"></textarea>

			<h4>내용</h4>
			<textarea rows="10" cols="75" name="episodeContents"></textarea>
			<br>
			<button type="reset" class="btn btn-sm">취소하기</button>
			<button type="submit" class="btn btn-sm btn-success">연재글 등록</button>
	
	</form>


	<script type="text/javaScript">
		function checkIt() {

			var eRegister = document.eRegister;

			if (!eRegister.episodeName.value) {
				alert("제목을 입력하세요");
				return false;
			}

			if (!eRegister.episodeContents.value) {
				alert("내용을 입력하세요");
				return false;
			}
			return true;
		}
	</script>
${box3 }
	
<%@ include file="footer.jspf"%>