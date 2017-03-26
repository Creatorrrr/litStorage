<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>토론장 Detail</title>

</head>
<body>
<%@ include file="header.jspf"%>
 <jsp:include page="litStorageSideNav.jsp">
 <jsp:param name="litStorage" value="${litStorage.id }"/>
 <c:choose>
 <c:when test="${litStorage.creator.id eq loginId}">
 <jsp:param name="isMaster" value="true"/>
 </c:when>
 <c:otherwise>
 <jsp:param name="isMaster" value="false"/>
 </c:otherwise>
 </c:choose>
</jsp:include>
<p>토론장</p>
<div>
<h1>토론주제 : ${discussionPlace.title }</h1>
<c:forEach items="${discussionPlace.discussionContents }" var="discussionContent" varStatus="status">
<div style="border: 1px solid #ccc;">
	No: ${discussionContent.id }
	아이디: ${ discussionContent.writer.name }
	 (<a href="${ctx }/discussionContent/delete.do?cid=${discussionContent.id}&pid=${discussionPlace.id }">삭제하기</a>)
	<br>
	내용: ${discussionContent.content } 
	
</div>
</c:forEach>


<br>
<h2>글쓰기</h2>
<form action="${ctx }/discussionContent/register.do" method="post">
<textarea name="discussionContentText" rows="4" cols="50"></textarea>
<input type="hidden" name="discussionPlaceId" value="${discussionPlace.id }">
<button id="discussionContentBtn">작성</button>
</form>
</div>

</body>
</html>