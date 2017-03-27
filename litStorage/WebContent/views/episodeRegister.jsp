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

<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>


	<div class="container">
		<div class="col-xs-12 col-md-12">
			<div class="col-xs-6 col-md-4">
				<div>
				<h1>${literature.litStorage.name}</h1>
				<h3>${literature.name }</h3>
				<jsp:include page="litStorageSideNav.jsp">
					 <jsp:param name="litStorage" value="${literature.litStorage.id }"/>
					 <jsp:param name="isMaster" value="${isMaster }"/>
					 <jsp:param name="onGroup" value="${onGroup }"/>
				</jsp:include>
				</div>
			</div>
			<div class="col-xs-12 col-md-8">
				<h1>연재 글 등록</h1>
				<div class="text-right"></div>
				<div class="panel panel-default">
					<div class="row">
						<form action="${ctx }/episode/register.do" method="post" name="eRegister" onsubmit="return checkIt()">
							<input type="hidden" name="literatureId" value="${literatureId }">
							<div class="col-md-3">
								<div>
									<h4>제목</h4>
									<textarea rows="1" cols="75" name="episodeName"></textarea>
								</div>
								<div>
									<h4>내용</h4>
									<textarea rows="10" cols="75" name="episodeContents"></textarea>
								</div>
							</div>
							<div class="text-right">
								<button type="reset" >취소하기</button>
								<button type="submit">연재글 등록</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
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

	
<%@ include file="footer.jspf"%>