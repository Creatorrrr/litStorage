<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청 확인 목록</title>

<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<style type="text/css">
div{border: 1px solid #ccc; }
</style> 

</head>
<body>

<div class="container">

<%@ include file="./header.jspf" %>

<div class="col-xs-12 col-md-12">

	<div class="col-xs-12 col-md-8">
		<h1>내게 온 요청 목록</h1>
		<div class="panel panel-default">
			
			<table class="table table-striped">
				<tbody>
						<c:choose>
							<c:when test="${inviteLists eq null || empty inviteLists}">
								<!--  request.setAttribute("inviteLists", list) null or not null-->
								<tr>
									<td colspan="6" align="center">요청 목록이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${inviteLists }" var="invite" varStatus="status">
										<tr><td>${status.count }.</td><tr>
										<tr><td>발신회원</td><td>${invite.sender.id}</td></tr>
										<tr><td>메시지</td><td>${invite.message}</td></tr>
										<tr><td>저장소 이름</td><td>${invite.litStorage.name }</td></tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
			</table>
		</div>
	</div>
</div>


</div>

</body>
</html>