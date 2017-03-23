<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- tag를 사용하기위해 선언 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet"> -->
<title>자유게시판</title>


<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<style type="text/css">
div{border: 1px solid #ccc; }
</style> 


</head>
<body>
	<%@ include file="/views/header.jspf"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>자유게시판 목록</h3>
				<a href="${ctx }/board/boardRegister.do">게시판 추가</a><br>
				<%-- <a href="${ctx }/litStorage/myList.do">무협</a><br> --%>
				<table class="table table-hover table-condensed">
					<colgroup>
						<col width="80" align="center">
						<col width="100">
						<col width="30%">
						<col width="*">
						<col width="100">
						<col width="100">
					</colgroup>
					<div class="container">

						<form action="${ctx }/post/searchList.do" method="post">
							<div class="text-right">
								<select name="selectContents">
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="hashtag">해시태그</option>
								</select> <input type="text" name="search" placeholder="검색 내용을 입력해주세요.">
								<button type="submit">검색</button>
							</div>
						</form>
					</div>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
							<%-- <c:if test = "${isAdmin }">    <!-- login했을 때만 보여지게 함 -->
								<th>게시물 등록</th>
							</c:if> --%>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${boards eq null || empty boards}">
								<!--  request.setAttribute("lectures", list) 값이 널인지 알아보는 것-->
								<tr>
									<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${boards }" var="board" varStatus="status">
									<tr>
										<td>${status.count }</td>
										<td><a href="${ctx }/post/postDetail.do?id=${board.id }">${board.title }</a></td>
										<td>${board.id}</td>
										<%-- <c:if test = "${isAdmin }"> 
											<td><a href="modify.do?id=${lecture.id }"class="btn btn-xs btn-warning">UPDATE</a></td>
											<td><a href="remove.do?id=${lecture.id }"class="btn btn-xs btn-danger">DELETE</a></td>
										</c:if> --%>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<%-- <c:if test = "${isAdmin }">  --%>
				<td><a class="btn btn-sm btn-success" href="${ctx}/views/postRegister.jsp?boardId=${board.id}">게시물 등록</a></td>
				<%-- </c:if>	 --%>
			</div>
		</div>
	</div>
</body>
</html>