<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html lang="ko">
	<head>
	<title>내 작품 저장소</title>
	<meta charset="utf-8">
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/layout.css" rel="stylesheet">
	<script src="resources/js/jquery-2.1.3.js"></script>
	<style>
		.litStorageBox {
			display: inline-block;
		    margin: 10px;
		    border: 3px solid #73AD21;
		}
	</style>
	</head>
	<body>
<%@ include file="header.jspf"%>
 <jsp:include page="litStorageSideNav.jsp">
 <jsp:param name="litStorage" value="${litStorage.id }"/>
</jsp:include>
	<h1>내 작품 저장소</h1>
		<c:choose>
			<c:when test="${litStorages eq null || empty litStorages }">
				<table border="1">
					<tr>
						<th colspan="7" class="text-center">등록된 작품저장소의 정보가 존재하지 않습니다.</th>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<c:forEach items="${litStorages }" var="litStorage">
					<div class="litStorageBox">
						<table border="1">
							<tr>
								<td>이름</td>
								<td><a href="${ctx}/litStorage/profile.do?id=${litStorage.id}">${litStorage.name }</a></td>
							</tr>
							<tr>
								<td>소개</td>
								<td>${litStorage.introduce }</td>
							</tr>
							
							<tr>
								<td>Email : </td>
								<td>${litStorage.creator.email }</td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</body>
</html>