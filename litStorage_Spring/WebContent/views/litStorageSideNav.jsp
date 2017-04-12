<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!-- side navigation bar area start -->

	<a href="${ctx }/litStorage/profile.do?id=${param.litStorage }"  class="list-group-item">프로필</a>
	<a href="${ctx }/literature/list.do?id=${param.litStorage }"  class="list-group-item">작품 목록</a>
	<c:if test="${param.onGroup eq true}">
		<a href="${ctx }/discussionPlace/list.do?litStorageId=${param.litStorage }" class="list-group-item">토론장</a>
	</c:if>
	<a href="${ctx }/litStorage/memberList.do?id=${param.litStorage }"  class="list-group-item">참가 회원 목록</a>
	<c:if test="${param.isMaster eq true}">
		<a href="${ctx }/member/search.do?litStorageId=${param.litStorage }"  class="list-group-item">회원초대</a>
	</c:if>

<!-- side navigation bar area end -->