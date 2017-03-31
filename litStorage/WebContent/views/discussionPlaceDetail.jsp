<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>토론장 상세페이지 - 소설 공동작업</title>


<%@ include file="header.jspf"%>

${box1 }
	<c:choose>
		<c:when test="${litStorage.creator.id eq loginId}">
			<c:set var="isMaster" value="true" />
		</c:when>
		<c:otherwise>
			<c:set var="isMaster" value="false" />
		</c:otherwise>
	</c:choose>
	
	<jsp:include page="litStorageSideNav.jsp">
		<jsp:param name="litStorage" value="${litStorage.id }"/>
		<jsp:param name="isMaster" value="${isMaster }"/>
		<jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>


${box2 }

	<h3>토론주제 : ${discussionPlace.title }</h3>
	<c:forEach items="${discussionPlace.discussionContents }" var="discussionContent" varStatus="status">
		<table class="table table-hover table-condensed" style="border:1px solid #ccc;">
			<tr><td>
				No: ${discussionContent.id } 아이디: ${ discussionContent.writer.name }
				<c:if test="${discussionContent.writer.id eq sessionScope.loginId || sessionScope.isAdmin}">
				 <a href="${ctx }/discussionContent/delete.do?cid=${discussionContent.id}&pid=${discussionPlace.id }&litStorageId=${litStorage.id}" class="btn btn-sm btn-warning label" style="float:right;margin:2px;">삭제하기</a>	
				</c:if>
			</td></tr>
			<tr><td>내용: ${discussionContent.content }</td></tr>
		</table>
	</c:forEach>

	<br>
	<h2>글쓰기</h2>
	<form action="${ctx }/discussionContent/register.do" method="post">
	<textarea name="discussionContentText" rows="4" cols="50"></textarea>
	<input type="hidden" name="discussionPlaceId" value="${discussionPlace.id }">
	<input type="hidden" name="litStorageId" value="${litStorage.id }"><br>
	<button id="discussionContentBtn" class="btn btn-sm btn-success">작성</button>
	</form>

${box3 }

	
<%@ include file="footer.jspf"%>