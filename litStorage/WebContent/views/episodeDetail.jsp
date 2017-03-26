<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 상세 - 소설 공동작업</title>

<%@ include file="header.jspf"%>

<c:choose>
 <c:when test="${episode.literature.creator.id eq loginId}">
 <c:set var="isMaster" value="true" />
 </c:when>
 <c:otherwise>
 <c:set var="isMaster" value="false" />
 </c:otherwise>
 </c:choose>

<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>
		<div class="col-xs-12 col-md-12">
			<div class="col-xs-6 col-md-4">
				<div>
				<h1>${episode.literature.litStorage.name}</h1>
				<h3>${episode.literature.name } - ${episode.title }</h3>
				<jsp:include page="litStorageSideNav.jsp">
					 <jsp:param name="litStorage" value="${episode.literature.litStorage.id }"/>
					 <jsp:param name="isMaster" value="${isMaster }"/>
				</jsp:include>
				</div>
			</div>
			<div class="col-xs-12 col-md-8">
				<h1>연재 글</h1>
				<div class="text-right">
					<button type="button"
						onclick="location.href='${ctx }/episode/modify.do?episodeId=${episode.id}'">수정하기</button>
					<button type="button"
						onclick="location.href='${ctx }/episode/delete.do?episodeId=${episode.id}'">삭제하기</button>
				</div>
				<div class="panel panel-default">
					<div class="row">
						<div class="col-md-3">
							<div>
								<h2>제목 : ${episode.title }</h2>
							</div>
							<div>
								<textarea rows="10" cols="75" name="episodeContents">${episode.contentFromGit }</textarea>
							</div>
						</div>
						<c:if test="${onGroup || sessionScope.isAdmin }">
							<div class="panel panel-default">
								<c:forEach items="${episode.histories }" var="history"
									varStatus="status">
									<a
										href="${ctx }/episode/changeHistoryDetail.do?historyId=${history.id}">
										<span>등록 날짜 : ${history.changeTime } </span> <span>변경 회원
											: ${history.editor.id } </span> <span>메시지 : ${history.message}
									</span>
									</a>
									<br>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	
	
	
<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");

		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}

	}
</script>
	
	
		
<%@ include file="footer.jspf"%>