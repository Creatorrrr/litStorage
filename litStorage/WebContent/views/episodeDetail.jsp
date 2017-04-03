<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 상세 - 소설 공동작업</title>

<%@ include file="header.jspf"%>
	



${box1 }
	<c:choose>
		<c:when test="${episode.literature.creator.id eq loginId}">
			<c:set var="isMaster" value="true" />
		</c:when>
		<c:otherwise>
			<c:set var="isMaster" value="false" />
		</c:otherwise>
	</c:choose>
	
	<jsp:include page="litStorageSideNav.jsp">
		 <jsp:param name="litStorage" value="${episode.literature.litStorage.id }"/>
		 <jsp:param name="isMaster" value="${isMaster }"/>
		 <jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>


${box2 }
	<h3>[${episode.literature.litStorage.name}] ${episode.literature.name }의 연재 글</h3>
	<h3>제목 : ${episode.title }</h3>
	<pre style="border:1px solid #ccc;min-height:300px;">${episode.contentFromGit }</pre>


	<c:if test="${onGroup || sessionScope.isAdmin }">
		<div class="panel panel-default">
			<c:forEach items="${episode.histories }" var="history"
				varStatus="status">
				<a href="${ctx }/episode/changeHistoryDetail.do?historyId=${history.id}">
					<span>등록 날짜 : ${history.changeTime } </span>
					<span>변경 회원 : ${history.editor.id } </span>
					<span>메시지 : ${history.message}</span>
				</a>
				<br>
			</c:forEach>
		</div>
	</c:if>
	<br>
	<button type="button" onclick="location.href='${ctx }/episode/modify.do?episodeId=${episode.id}'" class="btn btn-sm btn-success">수정하기</button>
	<button type="button" onclick="location.href='${ctx }/episode/delete.do?episodeId=${episode.id}'" class="btn btn-sm btn-warning">삭제하기</button>


	<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");
	
		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}
	
	}
	</script>
	
${box3 }	
		
<%@ include file="footer.jspf"%>