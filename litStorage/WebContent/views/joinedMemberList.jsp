<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>joinedMemberList - 소설 공동작업</title>

<%@ include file="header.jspf"%>


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
</jsp:include>
	<!--  Main member table start-->
	<table class="table table-hover table-condensed">
		<caption>메인 작가</caption>
		<colgroup>
			<col width="80" align="center">
			<col width="*">
		</colgroup>

		<tr>
			<td>아이디</td>
			<td>${litStorage.creator.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${litStorage.creator.name }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${litStorage.creator.email }</td>
		</tr>
	</table>
	<!-- Main member table over -->
	<!-- Sub member table start-->
	<table class="table table-hover table-condensed">
		<caption>참여 작가</caption>
		<colgroup>
			<col width="80" align="center">
			<col width="*">
		</colgroup>
		<c:forEach items="${ litStorage.participants}" var="member">
			<tr>
				<td>아이디</td>
				<td>${member.id }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.email }</td>
			</tr>
		</c:forEach>
	</table>
	<!-- sub member table over -->



<%@ include file="footer.jspf"%>