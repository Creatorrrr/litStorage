<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h3>[${episode.literature.litStorage.name}] ${episode.literature.name }의 연재글 복구</h3>

	<h2>제목 ${history.episode.title }</h2>
	<div class="row" style="margin:0;">
		<div class="col-md-6">
			<h3>이전 글</h3>
			<textarea style="width:100%;height:300px;"  name="beforeEpisodeContent">${history.contentFromGit }</textarea>
		</div>
		<div class="col-md-6">
			<h3>현재 글</h3>
			<textarea style="width:100%;height:300px;" name="nowEpisodeContent">${history.episode.contentFromGit }</textarea>
		</div>
	</div>
	<br>
	<div class="text-center" >
		<button type="button" onclick="location.href='${ctx }/episode/detail.do?episodeId=${history.episode.id}'" class="btn btn-sm btn-warning">이전상태</button>
		<button type="button" onclick="location.href='${ctx }/episode/rollback.do?historyId=${history.id}'" class="btn btn-sm btn-success">복구하기</button>
	</div>
	
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