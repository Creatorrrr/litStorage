<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html lang="ko">
	<head>
	<title>내 작품 저장소</title>
	<meta charset="utf-8">
	<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
	<%-- <link href="${ctx }/resources/css/layout.css" rel="stylesheet"> --%>
	<script src="${ctx }/resources/js/jquery-3.2.0.min.js"></script>
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

<!-- 사이드 네비게이션 링크 목록 -->
<a href="${ctx }/litStorage/myList.do">내 작품 저장소</a><br>
<a href="${ctx }/litStorage/allList.do">작품 저장소 전체 목록</a><hr>

<!-- 내 작품 저장소 목록 보여주기 -->
	<h1>내 작품 저장소</h1>
		<!-- 내 작품 저장소 페이지에서 작품 저장소 등록 버튼이 보임 -->
		<c:if test="${loginId ne null }">
			<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a>
			<br>
		</c:if>
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
								<td>생성자</td>
								<td>${litStorage.creator.id }</td>
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