<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>작품 목록</title>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");

		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}

	}
</script>

<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>
</head>

<body>
<c:choose>
 <c:when test="${litStorage.creator.id eq loginId}">
 <c:set var="isMaster" value="true" />
 </c:when>
 <c:otherwise>
 <c:set var="isMaster" value="false" />
 </c:otherwise>
 </c:choose>
<%@ include file="header.jspf"%>
 <jsp:include page="litStorageSideNav.jsp">
 <jsp:param name="litStorage" value="${litStorage.id }"/>
 <jsp:param name="isMaster" value="${isMaster }"/>
</jsp:include>
	<!-- <input type="button" id="deleteEpisode"
		onclick="location.href='../episode/list' "> -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<div class="col-md-6">
						<h1>제목</h1>
					</div>
				<!-- 	<div class="text-right">
						<button>로그인</button>
						<button>회원 정보 수정</button>
					</div> -->
				</div>
				<div class="row">
					<%-- <div class="col-md-6">
						<a href="javascript:;">자유게시판</a> <a
							href="${ctx }/litStorage/allList.do;">작품 저장소</a> <a
							href="javascript:;">작품 목록</a>
					</div> --%>
					<form action="${ctx }/episode/list.do" method="post">
						<div class="text-right">
							<select name="selectContents">
								<option value="writerId">작가 아이디</option>
								<option value="writerName">작가명</option>
								<option value="memberName">회원 이름</option>
							</select> <input type="text" name="search" placeholder="검색 내용을 입력해주세요.">
							<button type="submit">검색</button>
						</div>
					</form>
				</div>

			</div>
		</div>
		<div class="col-xs-12 col-md-12">
			<div class="col-xs-6 col-md-4">
				<h1>${litStorage.name }</h1>
				<div>
					<a href="javascript:;">프로필</a> <a href="javascript:;">작품 목록</a> <a
						href="javascript:;">토론장</a> <a href="javascript:;">참가 회원 목록</a> <a
						href="javascript:;">회원 초대</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-8">
				<h1>작품 목록</h1>
				<div class="text-right">
					<a href="${ctx }/literature/register.do?litStorageId=${litStorage.id}">작품 등록</a>
				</div>
				<div class="panel panel-default">
					<div>
						<table border="1">
							<colgroup>
								<col width="50">
								<col width="100">
								<col width="500">
								<col width="100">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>장르</th>
									<th>제목</th>
									<th>작성자</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${litStorage.literatures }" var="literature" varStatus="status">
									<td>${status.count }</td>
									<td>${literature.genre }</td>
									<td>
										<a href="${ctx}/episode/list.do?literatureId=${literature.id}">${literature.name }</a>
									</td>
									<td>${literature.creator.name }</td>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>