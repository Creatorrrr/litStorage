<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
</head>
<body>



<!-- side navigation bar area -->

<a href="${ctx }/litStorage/profile.do?id=${param.litStorage }">프로필</a>||
<a href="${ctx }/literature/list.do?id=${param.litStorage }">작품 목록</a>||
<a href="${ctx }/discussionPlace/list.do?litStorageId=${param.litStorage }">토론장</a>||
<a href="${ctx }/litStorage/memberList.do?id=${param.litStorage }">참여 회원 목록</a>||
<c:if test="${param.isMaster eq true}">
<a href="${ctx }/member/search.do?litStorageId=${param.litStorage }">회원 초대</a>
</c:if>
</body>
</html>
